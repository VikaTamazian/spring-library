package org.tamazian.library.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.tamazian.library.form.UserForm;
import org.tamazian.library.service.UserService;

@Component
public class SignupValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm user = (UserForm) target;

        ValidationUtils.rejectIfEmpty(errors, "username", "notEmpty.username");
        ValidationUtils.rejectIfEmpty(errors, "password", "notEmpty.password");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "notEmpty.confirmPassword");

        if (user.getPassword() != null && user.getConfirmPassword() != null &&
            !user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "notMatch.confirmPassword");
        }

        if (userService.userExists(user.getUsername())) {
            errors.rejectValue("username", "exists.username");
        }
    }
}
