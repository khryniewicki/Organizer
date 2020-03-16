package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Progress;
import com.khryniewicki.organizer.main_content.model.Sprint;
import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.services.ProgressServices;
import com.khryniewicki.organizer.main_content.services.ProjectBarServices;
import com.khryniewicki.organizer.main_content.services.SprintService;
import com.khryniewicki.organizer.main_content.services.TaskServices;
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
    private final ProjectBarServices projectBarServices;
    private final TaskServices taskServices;
    private final SprintService sprintService;
    @GetMapping("/dashboard")
    public String showDashBoard(Model model) {
        return "main/dashBoard";
    }



    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("progress_steps", progressServices.findAllProgress());
        model.addAttribute("projectList", projectBarServices.getAllProjekts());
        model.addAttribute("sprintList",sprintService.findAll());
        model.addAttribute("sprint",new Sprint());
        model.addAttribute("taskList",taskServices.taskListBySprintId(1L));
    }
}
