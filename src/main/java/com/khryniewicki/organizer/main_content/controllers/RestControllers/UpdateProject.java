package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.DTO.ProjectDTO;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class UpdateProject {
    private final ProjectService projectService;

    @GetMapping("/star/{projectId}")
    public ProjectDTO updateProject(@PathVariable("projectId")  Long projectId){
        Project project = projectService.findProject(projectId);
        boolean updatedStarred = !project.isStarred();
        project.setStarred(updatedStarred);
        projectService.save(project);
        return projectService.findProjectAndTransferToDTO(projectId);
    }
}
