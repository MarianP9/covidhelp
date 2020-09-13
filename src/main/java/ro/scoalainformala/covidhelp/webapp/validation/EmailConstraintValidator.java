package ro.scoalainformala.covidhelp.webapp.validation;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class EmailConstraintValidator implements ConstraintValidator<Email, String> {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        Set<Account> list = new HashSet<>();
            if(accountRepository == null){
                return true;
            }
        accountRepository.findAll().forEach(list::add);

        Set<String> e = new HashSet<>();
        list.stream().map(Account::getEmail).forEach(e::add);

        for (String f : e) {
            if (f.contentEquals(email)) {
                return false;
            }
        }

        if (email.isEmpty()){
            return false;
        }

        return true;
    }
}