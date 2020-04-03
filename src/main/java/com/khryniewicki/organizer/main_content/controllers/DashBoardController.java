package com.khryniewicki.organizer.main_content.controllers;

import com.khryniewicki.organizer.main_content.model.Sprint;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.services.*;
import com.khryniewicki.organizer.registration_login_logout.DTO.TaskDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String showDashBoard(@RequestParam("id") Long projectId, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session.getAttribute("appUser") == null) return "redirect:/logout";
        hrefService.saveOrUpdate(projectId);

        model.addAttribute("taskList", taskServices.taskListByProjectId(projectId));
        model.addAttribute("actualDashBoard", projectService.findProject(projectId));
        model.addAttribute("usersAssignedToProject", userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute("usersAssignedToProjectApartActiveUser",userService.getAllUsersAssignedToProjectApartActiveUser(projectId));

        return "main/dashBoard";
    }

    @GetMapping("/createTask")
    public String addCard(@RequestParam("id") Long projectId, Model model) {
        model.addAttribute("newTask", taskServices.newTaskDtoWithAssignedProjectId(projectId));
        model.addAttribute("taskList", taskServices.taskListByProjectId(projectId));
        model.addAttribute("actualDashBoard", projectService.findProject(projectId));
        model.addAttribute("usersAssignedToProject", userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute("usersAssignedToProjectApartActiveUser",userService.getAllUsersAssignedToProjectApartActiveUser(projectId));
        return "fragments_dashboard/createTask";
    }



    @PostMapping("/createTask")
    public String postCard(@ModelAttribute("newTask") @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        Long projectId = taskServices.getProjectIdFromTaskDTO(taskDTO);
        if (bindingResult.hasErrors()) {
            return "fragments_dashboard/createTask";
        }
        taskServices.createTaskUsingTaskDTO(taskDTO);
        return "redirect:/dashboard?id=" + projectId;
    }



    @GetMapping("/edittask")
    public String editCard(@RequestParam("id") Long id, Model model) {
        TaskDTO taskDTO = taskServices.getTaskDtoFromTask(id);
        model.addAttribute("oldTask", taskDTO);
        Long projectId = taskServices.getProjectIdFromTaskDTO(taskDTO);
        model.addAttribute("taskList", taskServices.taskListByProjectId(projectId));
        model.addAttribute("actualDashBoard", projectService.findProject(projectId));
        model.addAttribute("usersAssignedToProject", userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute("usersAssignedToProjectApartActiveUser",userService.getAllUsersAssignedToProjectApartActiveUser(projectId));
        return "fragments_dashboard/editTask";
    }

    @PostMapping("/edittask")
    public String editCard(@ModelAttribute("oldTask") TaskDTO taskDTO, @RequestParam("id") Long id) {
        taskServices.updateTaskUsingDTO(taskDTO, id);
        return "redirect:/dashboard?id=" + taskDTO.getProject().getId();
    }

    @GetMapping("/deletetask")
    public String deleteCard(@RequestParam("id") Long id) {
        Long projectId = taskServices.getProjectIdFromTask(id);
        taskServices.deleteTask(id);
        return "redirect:/dashboard?id=" + projectId;
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null) appUser = (User) session.getAttribute("appUser");

        if (appUser != null) {

            model.addAttribute("ActualUser", appUser);
            model.addAttribute("ActualUserInitialLetters", userService.getInitialLetters(appUser));
            model.addAttribute("actualDashBoard", hrefService.getLastProject());

            model.addAttribute("progress_steps", progressServices.findAllProgress());
            model.addAttribute("projectList", projectService.getAllProjectsForUser());

            model.addAttribute("sprintList", sprintService.findAll());
            model.addAttribute("sprint", new Sprint());

            model.addAttribute("taskList", taskServices.taskListByProjectId());

            model.addAttribute("userList", userService.getAllUsersApartActiveUser());

        }

    }

}
