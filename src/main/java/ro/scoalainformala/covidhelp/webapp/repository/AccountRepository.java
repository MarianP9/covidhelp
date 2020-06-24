package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
