package app;

import app.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/accounts/{userId}")
public class AccountController {

    HashMap<Integer, Long> cache = new HashMap<>();

    @Autowired
    AccountServiceImpl service;

    @GetMapping
    public Long getUserID(@PathVariable Integer userId) {
        if (cache.containsKey(userId)){
            return cache.get(userId);
        }
        return service.getAmount(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserID(@PathVariable Integer userId, @RequestBody Long value) {
        service.addAmount(userId, value);
        cache.remove(userId);//renew the value by id
    }
}
