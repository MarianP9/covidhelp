package ro.scoalainformala.covidhelp.webapp.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;

import java.util.Optional;

@Service
@Log4j2
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(email);
        account.orElseThrow(() -> new UsernameNotFoundException("not found"));
        return account.map(MyUserDetails::new).get();
    }

}
