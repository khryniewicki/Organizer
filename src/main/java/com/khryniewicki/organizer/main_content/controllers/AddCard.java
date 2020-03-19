package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Task;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddCard {

    @GetMapping("/addcard")
    public String addCard(Model model) {
        System.out.println("fetch check");
        return "main/addCard";
    }
    @PostMapping ("/addcard")
    public String postCard(@ModelAttribute Task task){
        return "redirect:/dashboard";
    }
}
