package com.khryniewicki.organizer.main_content.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Data
@Table
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    @Size(max=50)
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Sprint sprint;
    @ManyToOne
    @JsonIgnore
    private Project project;
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
                ", priority=" + priority +
                ", storyPoints=" + storyPoints +
                ", typeOfStory=" + typeOfStory +
                ", progress='" + progress + '\'' +
                ", users=" + users +
                '}';
    }


}
