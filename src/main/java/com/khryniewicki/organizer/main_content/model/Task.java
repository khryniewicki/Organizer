package com.khryniewicki.organizer.main_content.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Data
@Table
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Sprint sprint;
    private String project_name;
    @Enumerated
    private Priority priority;
    private Long storyPoints;
    @Enumerated
    private TypeOfStory typeOfStory;
    private String progress;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +

                ", project_name='" + project_name + '\'' +
                ", priority=" + priority +
                ", storyPoints=" + storyPoints +
                ", typeOfStory=" + typeOfStory +
                ", progress='" + progress + '\'' +

                '}';
    }
}
