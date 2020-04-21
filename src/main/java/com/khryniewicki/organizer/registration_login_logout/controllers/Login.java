package com.khryniewicki.organizer.registration_login_logout.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@RequiredArgsConstructor
@Controller
public class Login {


    @GetMapping({"/login"})
    public String projects(Model model) {
        return "login/loginPage";
    }



    @GetMapping("/login-error")
    public String login_error(Model model) {
        model.addAttribute("loginError",true);
        return "login/loginPage";
    }


}