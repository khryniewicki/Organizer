package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.main_content.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddUserToProjectRestController {

    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping("/project/{idproject}/{user}")
    public void addUserToProject(@PathVariable("idproject") Long idproject, @PathVariable ("user") Long  iduser){

        projectService.addUserToProject(idproject,iduser);
    }
}
