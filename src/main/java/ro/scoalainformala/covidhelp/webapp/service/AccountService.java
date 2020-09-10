package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.domain.Account;

public interface AccountService {
    int getVolunteersCount();

    String getEmail();
    Account getAccountById(long id);
    Account getAccountByEmail(String email);
}
