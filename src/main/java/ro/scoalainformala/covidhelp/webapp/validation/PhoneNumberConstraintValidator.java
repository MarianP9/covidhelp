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
public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {

    @Autowired
    AccountRepository accountRepository;

    public boolean isValid(String phoneNumber, ConstraintValidatorContext cvc) {

        Set<Account> list = new HashSet<>();
        if(accountRepository == null){
            return true;
        }
        accountRepository.findAll().forEach(list::add);

        Set<String> e = new HashSet<>();
        list.stream().map(Account::getPhoneNumber).forEach(e::add);

        for (String f : e) {
            if (f.contentEquals(phoneNumber)) {
                return false;
            }
        }
        if (!(phoneNumber.length() == 9 && phoneNumber.matches("[0-9]+"))){
            return false;
        }
        return true;
    }
}