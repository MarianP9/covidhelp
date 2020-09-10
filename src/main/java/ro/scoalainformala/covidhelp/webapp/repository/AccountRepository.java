package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Role;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    int countByRole(Role role);

    Account getAccountById(long id);
    Account getAccountByEmail(String email);
}
