package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Role;

public interface AccountRepository extends CrudRepository<Account, Long> {

    int countByRole(Role role);

    Account getAccountById(long id);
}
