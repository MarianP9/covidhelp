package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Role;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }


    // with this method you get access to the login information
    public String getEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }

    @Override
    public int getVolunteersCount() {
        return repository.countByRole(Role.ROLE_VOLUNTEER);
    }

    @Override
    public Account getAccountById(long id) {
        return repository.getAccountById(id);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return repository.getAccountByEmail(email);
    }
}
