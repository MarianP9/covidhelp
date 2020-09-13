package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.config.Encoder;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;
import ro.scoalainformala.covidhelp.webapp.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    final AccountRepository accountRepository;

    @Autowired
    Encoder encoder;

    public GeneralServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // this method will save the new account
    @Override
    public void add(Account account) {
        account.setEmail(account.getEmail().toLowerCase());
        encoder.encrypt(account);
        accountRepository.save(account);
    }
}
