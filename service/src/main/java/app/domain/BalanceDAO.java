package app.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceDAO extends CrudRepository<Balance, Integer> {
}
