package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.main_content.services.UserService;
import com.khryniewicki.organizer.main_content.DTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
@Controller
public class ProjectsController {

    private final ProjectService projectService;
    private final UserService userService;
    @GetMapping("/projects")
    public String showProjects(Model model, HttpServletRequest request) {
        return "fragments_projects/browserProject";
    }

    @GetMapping("/createProject")
    public String createProject(Model model) {
        model.addAttribute("newProject", new ProjectDTO());
        return "fragments_projects/addProject";
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute("newProject") @Valid ProjectDTO projectDTO, BindingResult bindingResult) {
        System.out.println(projectDTO);
        if (bindingResult.hasErrors()) {
            bindingResult.rejectValue("avatar","error.newProject","Select avatar");
            return "fragments_projects/addProject";
        }
        projectService.createProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/editproject")
    public String editProject(@RequestParam("id") Long projectId, Model model) {

        model.addAttribute("oldProject", projectService.findProjectAndTransferToDTO(projectId));
        model.addAttribute("usersAssignedToProject", userService.getAllUsersAssignedToProject(projectId));

        return "fragments_projects/editProject";
    }

    @PostMapping("/editproject")
    public String editProject(Model model, @ModelAttribute("oldProject") ProjectDTO projectDTO,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.rejectValue("avatar","error.newProject","Select avatar");
            return "fragments_projects/addProject";
        }
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
