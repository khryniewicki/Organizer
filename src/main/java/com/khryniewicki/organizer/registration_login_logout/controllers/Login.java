package com.khryniewicki.organizer.registration_login_logout.controllers;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.registration_login_logout.services.LoggingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Controller
public class Login {

    private  final LoggingUserService loggingUserService;

    @GetMapping({"/","/login"})
    public String projects(Model model) {
        return "login/loginPage";
    }



    @GetMapping("/login-error")
    public String login_error(Model model) {
        return "login/loginErrorPage";
    }


}