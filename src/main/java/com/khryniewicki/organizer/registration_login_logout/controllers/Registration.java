package com.khryniewicki.organizer.registration_login_logout.controllers;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.registration_login_logout.DTO.UserDTO;
import com.khryniewicki.organizer.registration_login_logout.services.LoggingUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
@Controller
public class Registration {

    private final LoggingUserService loggingUserService;

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("newUser", userDTO);
        return "login/registrationPage";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("newUser") @Valid UserDTO userDTO, BindingResult result, Errors errors) {
        User registered = new User();

        if (result.hasErrors()) {
            log.error("errors");
            return "login/registrationPage";
        }

        if (!result.hasErrors()) {
        log.info("addUser");
            registered = createUserAccount(userDTO);
        }

        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        return "redirect:/projects";
    }

    private User createUserAccount(UserDTO userDTO) {
        User registered = null;
        try {
            log.info("createUser");
            registered = loggingUserService.registerNewUserAccount(userDTO);
        } catch (IOException e) {
            return null;
        }
        return registered;
    }
}


