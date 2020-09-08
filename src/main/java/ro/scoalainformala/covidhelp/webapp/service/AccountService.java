package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.domain.Account;

public interface AccountService {
    int getVolunteersCount();

    Account getAccountById(long id);
}
