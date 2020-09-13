package ro.scoalainformala.covidhelp.webapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BirthDateConstraintValidator implements ConstraintValidator<BirthDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }

        LocalDate old = LocalDate.parse("1900-01-01");
        LocalDate now = LocalDate.now();

        return !date.isBefore(old) && !date.isAfter(now);
    }

}
