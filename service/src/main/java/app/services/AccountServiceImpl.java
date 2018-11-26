package app.services;

import app.domain.Balance;
import app.domain.BalanceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    BalanceDAO balanceDAO;

    @Override
    public Long getAmount(Integer id) {
        return balanceDAO.findById(id).map(Balance::getValue).orElse(0L);
    }

    @Override
    public synchronized void addAmount(Integer id, Long value) {
        Balance balance = balanceDAO.findById(id).orElseGet(Balance::new);
        balance.setValue(balance.getValue() + value);

        balanceDAO.save(balance);

    }
}
