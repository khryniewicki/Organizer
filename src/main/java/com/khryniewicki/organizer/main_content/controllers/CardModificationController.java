package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.services.HrefService;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.main_content.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequiredArgsConstructor
@Controller
public class CardModificationController {
    private  final ProjectService projectService;
    private final TaskServices taskServices;
    private final HrefService hrefService;

    @GetMapping("/createTask")
    public  String addCard(@RequestParam("id") Long id, Model model){
        Task task = new Task();
        task.setProject(projectService.findProject(id));
        model.addAttribute("newTask",task);
        return "fragments_dashboard/createTask";
    }

    @PostMapping("/createTask")
    public String postCard(@ModelAttribute Task task) {
        taskServices.saveTask(task);
        return "redirect:/dashboard?id="+task.getProject().getId();
    }

    @GetMapping("/edittask")
    public String editCard(@RequestParam("id") Long id, Model model) {
        model.addAttribute("oldTask",taskServices.findTask(id));
        return "fragments_dashboard/editTask";
    }
    @PostMapping("/edittask")
    public String editCard( Model model, @ModelAttribute("oldTask") Task task,@RequestParam("id") Long id) {
        System.out.println(task);
        taskServices.updateTask(task,id);
        return "redirect:/dashboard?id="+task.getProject().getId();
    }

    @GetMapping("/deletetask")
    public String deleteCard(@RequestParam("id")Long id){
        Long idProject = taskServices.findTask(id).getProject().getId();
        taskServices.deleteTask(id);
        return "redirect:/dashboard?id="+idProject;
    }
    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {


    }
}

