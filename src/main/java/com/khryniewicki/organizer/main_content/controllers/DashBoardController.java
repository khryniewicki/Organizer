package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Progress;
import com.khryniewicki.organizer.main_content.services.ProgressServices;
import com.khryniewicki.organizer.main_content.services.ProjectBarServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Data
@RequiredArgsConstructor
@Controller
public class DashBoardController {
    private final ProgressServices progressServices;
    private final ProjectBarServices projectBarServices;

    @GetMapping("/dashboard")
    public String showDaszBoard(Model model) {
        return "main/dashBoard";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("progress_steps", progressServices.findAllProgress());
        model.addAttribute("projectList", projectBarServices.getAllProjekts());

    }
}
