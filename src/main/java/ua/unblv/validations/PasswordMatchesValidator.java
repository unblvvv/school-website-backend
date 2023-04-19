package ua.unblv.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.unblv.annotations.PasswordMatches;
import ua.unblv.payload.request.SignUp;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignUp userSignUp = (SignUp) o;
        return userSignUp.getPassword().equals(userSignUp.getConfirmPassword());
    }
}
