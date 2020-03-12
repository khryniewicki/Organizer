package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.services.ProjectBarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProjectBarController {
private final ProjectBarServices projectBarServices;

    @GetMapping()
    public String showProjectBar (Model model){
        model.addAttribute("projects", projectBarServices.getAllProjekts() );
        return "fragments/project_menu";
    }
}
