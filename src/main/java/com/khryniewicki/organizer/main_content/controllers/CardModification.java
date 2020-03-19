package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.services.HrefService;
import com.khryniewicki.organizer.main_content.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequiredArgsConstructor
@Controller
public class CardModification {
    private final TaskServices taskServices;
    private final HrefService hrefService;

    @PostMapping("/addcard")
    public String postCard(@ModelAttribute Task task) {
        taskServices.saveTask(task);
        return "redirect:/dashboard?name="+hrefService.getLast();
    }
    @GetMapping("/edittask")
    public String editCard(@RequestParam("id") Long id, Model model, @ModelAttribute Task task) {
        model.addAttribute("oldTask",taskServices.findTask(id));
        return "fragments/edit_card";
    }
    @PostMapping("/edittask")
    public String editCard( Model model, @ModelAttribute("oldTask") Task task,@RequestParam("id") Long id) {
        taskServices.updateTask(task,id);
        return "redirect:/dashboard?name="+hrefService.getLast();
    }
    @GetMapping("/deletetask")
    public String deleteCard(@RequestParam("id")Long id){
        taskServices.deleteTask(id);
        return "redirect:/dashboard?name="+hrefService.getLast();
    }
    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        Task task = new Task();
        task.setProject(hrefService.getLastProject());
        model.addAttribute("newTask",task);

    }
}

