package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.services.HrefService;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.main_content.services.TaskServices;
import com.khryniewicki.organizer.main_content.services.UserService;
import com.khryniewicki.organizer.registration_login_logout.DTO.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@ControllerAdvice
@RequiredArgsConstructor
@Controller
public class CardModificationController {
    private  final ProjectService projectService;
    private final TaskServices taskServices;
    private final UserService userService;

    @GetMapping("/createTask")
    public  String addCard(@RequestParam("id") Long id, Model model){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setProject(projectService.findProject(id));
        model.addAttribute("newTask",taskDTO);
        model.addAttribute("taskList", taskServices.taskListByProjectId(id));
        model.addAttribute("ActualDashBoard", projectService.findProject(id));
        model.addAttribute("usersToProject",userService.getAllUsersToProject(id));
        return "fragments_dashboard/createTask";
    }

    @PostMapping("/createTask")
    public String postCard(@ModelAttribute ("newTask") @Valid TaskDTO taskDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "fragments_dashboard/createTask";
        }
        taskServices.saveTask(taskDTO);
        return "redirect:/dashboard?id="+taskDTO.getProject().getId();
    }

    @GetMapping("/edittask")
    public String editCard(@RequestParam("id") Long id,  Model model) {
        Task taskDTO = taskServices.findTask(id);
        model.addAttribute("oldTask",taskDTO);
        Long project_id = taskDTO.getProject().getId();
        model.addAttribute("taskList", taskServices.taskListByProjectId(project_id));
        model.addAttribute("ActualDashBoard", projectService.findProject(project_id));
        model.addAttribute("usersToProject",userService.getAllUsersToProject(project_id));
        return "fragments_dashboard/editTask";
    }
    @PostMapping("/edittask")
    public String editCard( Model model, @ModelAttribute("oldTask") TaskDTO taskDTO,@RequestParam("id") Long id) {
//        System.out.println(task);
        taskServices.updateTaskUsingDTO(taskDTO,id);
        return "redirect:/dashboard?id="+taskDTO.getProject().getId();
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

