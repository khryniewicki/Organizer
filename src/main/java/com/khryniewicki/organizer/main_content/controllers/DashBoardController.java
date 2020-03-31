package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Sprint;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.services.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
@Data
@RequiredArgsConstructor
@Controller
public class DashBoardController {
    private final ProgressServices progressServices;
    private final ProjectService projectService;
    private final TaskServices taskServices;
    private final SprintService sprintService;
    private final HrefService hrefService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String showDashBoard(@RequestParam("id") Long id, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session.getAttribute("appUser") == null) return "redirect:/logout";
        hrefService.saveOrUpdate(id);

        model.addAttribute("taskList", taskServices.taskListByProjectId(id));
        model.addAttribute("ActualDashBoard", projectService.findProject(id));
        model.addAttribute("usersToProject",userService.getAllUsersToProject(id));
        return "main/dashBoard";
    }


    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null) appUser = (User) session.getAttribute("appUser");

        if (appUser != null) {

            model.addAttribute("ActualUser", appUser);
            model.addAttribute("ActualUserInitialLetters", userService.getInitialLetters(appUser));
            model.addAttribute("ActualDashBoard", hrefService.getLastProject());

            model.addAttribute("progress_steps", progressServices.findAllProgress());
            model.addAttribute("projectList", projectService.getAllProjectsForUser(appUser));

            model.addAttribute("sprintList", sprintService.findAll());
            model.addAttribute("sprint", new Sprint());

            model.addAttribute("taskList", taskServices.taskListByProjectId());

            model.addAttribute("userList",userService.getAllUsersApartActiveUser());

        }

    }

}
