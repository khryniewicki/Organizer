package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.registration_login_logout.DTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class BrowseProjectController {
private final ProjectService projectService;

    @GetMapping("/projects")
    public String showProjects (Model model, HttpServletRequest request)  {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("appUser")==null) return "redirect:/logout";
        model.addAttribute("projects", projectService.getAllProjekts() );
        model.addAttribute("avatar_project",ProjectService.CreateiconRandomViewer());
        return "fragmentsProjects/projects_panel";
    }

    @PostMapping("/createProject")
    public String createProject (@ModelAttribute ("newProject") @Valid ProjectDTO projectDTO, BindingResult bindingResult){
        projectService.createProject(projectDTO);
        return "redirect:/projects";
    }
}
