package com.khryniewicki.organizer.registration_login_logout.DTO;

import com.khryniewicki.organizer.main_content.model.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TaskDTO {

    private Long idTask;

    @NotNull
    @Size(min=2, max=100)
    private String name;
    private String description;


    private Sprint sprint;
    private Project project;
    private Priority priority;
    private Long storyPoints;
    private TypeOfStory typeOfStory;
    private String progress;

    private User user;

}
