package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Sprint;
import com.khryniewicki.organizer.main_content.services.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/dashboard")
    public String showDashBoard(@RequestParam ("name") String name, Model model) {
            hrefService.saveHref(name);
        model.addAttribute("taskList",taskServices.taskListByProjectName(name));
        model.addAttribute("ActualDashBoard",projectService.findProject(name));
        return "main/dashBoard";
    }




    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("progress_steps", progressServices.findAllProgress());
        model.addAttribute("projectList", projectService.getAllProjekts());
        model.addAttribute("sprintList",sprintService.findAll());
        model.addAttribute("sprint",new Sprint());
        model.addAttribute("taskList",taskServices.taskListByProjectName());
        model.addAttribute("ActualDashBoard",hrefService.getLastProject());

    }
}
