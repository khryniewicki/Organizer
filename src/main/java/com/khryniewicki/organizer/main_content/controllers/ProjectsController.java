package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.registration_login_logout.DTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ProjectsController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public String showProjects(Model model, HttpServletRequest request) {
        return "fragmentsProjects/browserProject";
    }

    @GetMapping("/createProject")
    public String createProject(Model model) {
        model.addAttribute("newProject", new ProjectDTO());
        return "fragmentsProjects/addProject";
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute("newProject") @Valid ProjectDTO projectDTO, BindingResult bindingResult) {

        projectService.createProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/editproject")
    public String editProject(@RequestParam("id") Long id, Model model, @ModelAttribute Project Project) {
        model.addAttribute("oldProject", projectService.findProjectAndTransferToDTO(id));
        return "fragmentsProjects/editProject";
    }

    @PostMapping("/editproject")
    public String editProject(Model model, @ModelAttribute("oldProject") ProjectDTO projectDTO) {
        projectService.updateProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null) appUser = (User) session.getAttribute("appUser");

        if (appUser != null) {
            model.addAttribute("avatarList", UtillClass.getListOfIconTitles());
            model.addAttribute("allAdminsInitialsList", projectService.getProjectAdminNameAndSurname());
        }
    }
}
