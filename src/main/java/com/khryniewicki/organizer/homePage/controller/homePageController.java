package com.khryniewicki.organizer.homePage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class homePageController {

    @GetMapping({"/home","/"})
    public String showHomePage(Model model) {
        model.addAttribute("presentYear", LocalDateTime.now().getYear());
        return "homePage/index";
    }
}
