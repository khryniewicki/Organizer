package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.main_content.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddUserToProjectRestController {

    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping("/project/{projectId}/{userId}")
    public String addUserToProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        projectService.addUserToProject(projectId, userId);
        return "Result: positive";
    }

    @GetMapping("/dashboard/allusers")
    public List<User> getAllUsers() {
        return userService.getAllUsersApartActiveUser();
    }

}
