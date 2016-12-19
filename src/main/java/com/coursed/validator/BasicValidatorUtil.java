package com.coursed.validator;

import com.coursed.dto.BasicUserRegistrationForm;
import com.coursed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import javax.validation.ValidationException;

/**
 * Created by Trach on 12/16/2016.
 */
public class BasicValidatorUtil {

    public static void validateEmail(BasicUserRegistrationForm form, UserService userService) throws ValidationException {
        String reg = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,5})$";
        if (!form.getEmail().matches(reg)) {
            throw new ValidationException("Wrong characters in email");
        }
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            throw new ValidationException("Email exists");
        }
    }

    public static void validatePasswords(BasicUserRegistrationForm form) throws ValidationException {
        String reg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
        if(!form.getPassword().matches(reg)) {
            throw new ValidationException("Password is too simple");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new ValidationException("Passwords do not match");
        }
    }

    public static void validateNames(BasicUserRegistrationForm form) throws ValidationException {
        String reg = "^[А-ЯІЄ][а-яіє]{1,15}"; // TODO ukr dictionary
        if (!form.getFirstName().matches(reg)) {
            throw new ValidationException("First name is wrong");
        }
        if (!form.getLastName().matches(reg)) {
            throw new ValidationException("Last name is wrong");
        }
        if (!form.getPatronymic().matches(reg)) {
            throw new ValidationException("Patronymic is wrong");
        }
    }
}
