package app.services;


public interface AccountService {
    Long getAmount(Integer id);
    void addAmount(Integer id, Long value);
}
