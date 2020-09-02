package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Role;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }


    @Override
    public int getVolunteersCount() {
        return repository.countByRole(Role.VOLUNTEER);
    }
}
