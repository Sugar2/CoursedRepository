package com.coursed.controller.mvc;

import com.coursed.dto.*;
import com.coursed.model.Student;
import com.coursed.model.auth.User;
import com.coursed.model.auth.VerificationToken;
import com.coursed.registration.OnRegistrationCompleteEvent;
import com.coursed.security.SecurityService;
import com.coursed.service.UserService;
import com.coursed.validator.UserRegistrationFormValidator;
import com.coursed.validator.UserStudentRegistrationFormValidator;
import com.coursed.validator.UserTeacherRegistrationFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;

/**
 * Created by Hexray on 13.11.2016.
 */
@Controller
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserStudentRegistrationFormValidator userStudentRegistrationFormValidator;

    @Autowired
    private UserTeacherRegistrationFormValidator userTeacherRegistrationFormValidator;


    @InitBinder("studentForm")
    public void initStudentBinder(WebDataBinder binder) {
        binder.addValidators(userStudentRegistrationFormValidator);
    }

    @InitBinder("teacherForm")
    public void initTeacherBinder(WebDataBinder binder) {
        binder.addValidators(userTeacherRegistrationFormValidator);
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(required = false) String error) {
        LOGGER.debug("Getting login page, error={}", error);
        model.addAttribute("error", error);
        return "auth/login";
    }

    @PostMapping("/registration-student")
    public String regsterStudent(@Valid @ModelAttribute("studentForm") UserStudentRegistrationForm userStudentRegistrationForm,
                             BindingResult bindingResult, final HttpServletRequest request, Model model) {

        LOGGER.debug("Processing user registration userForm={}, bindingResult={}", userStudentRegistrationForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        User registered;
        try{
            registered = userService.registerStudent(userStudentRegistrationForm);
        } catch(DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            return "auth/login";
        }

        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            model.addAttribute("emailMessage", registered.getEmail());
        } catch (final Exception ex) {
            LOGGER.warn("Unable to register user", ex);
        }

        return "/auth/verifyYourAccount";
    }

    @PostMapping("/registration-teacher")
    public String registerTeacher(@Valid @ModelAttribute("teacherForm") UserTeacherRegistrationForm userTeacherRegistrationForm,
                             BindingResult bindingResult, final HttpServletRequest request, Model model) {

        LOGGER.debug("Processing user registration userForm={}, bindingResult={}", userTeacherRegistrationForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        User registered;
        try{
            registered = userService.registerTeacher(userTeacherRegistrationForm);
        } catch(DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            return "auth/login";
        }

        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            model.addAttribute("emailMessage", registered.getEmail());
        } catch (final Exception ex) {
            LOGGER.warn("Unable to register user", ex);
        }

        System.out.println(registered.getEmail());

        return "/auth/verifyYourAccount";
    }

    @GetMapping("/registration-confirm")
    public String confirmRegistration(Model model, @RequestParam("token") String token, RedirectAttributes redAtt) {
        LOGGER.debug("Receiving confirmation token: {}", token);

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) { // TODO
            LOGGER.debug("Invalid token received: {}", token);
            String message = "Invalid token received: " + token;
            model.addAttribute("message", message);
            return "/auth/badUser";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            LOGGER.debug("Verification token expired for user: {}", user);
            String messageValue = "Verification token expired for user: " + user.getEmail();
            model.addAttribute("message", messageValue);
            return "/auth/badUser";
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        LOGGER.debug("Received verification from user: {}", user);
        redAtt.addFlashAttribute("message", "Ви активували свій акаунт. Увійдіть.");
        return "redirect:/login";
    }
}
