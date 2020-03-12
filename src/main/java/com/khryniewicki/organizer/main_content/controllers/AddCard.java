package com.khryniewicki.organizer.main_content.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddCard {

    @GetMapping("/addcard")
    public String addCard(Model model) {
        return "main/addCard";
    }
}
