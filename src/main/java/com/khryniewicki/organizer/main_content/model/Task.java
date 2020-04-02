package com.khryniewicki.organizer.main_content.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Sprint sprint;
    @ManyToOne()
    @JsonIgnore
    private Project project;
    @Enumerated
    private Priority priority;
    private Long storyPoints;
    @Enumerated
    private TypeOfStory typeOfStory;
    private String progress;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sprint=" + sprint +
                ", priority=" + priority +
                ", storyPoints=" + storyPoints +
                ", typeOfStory=" + typeOfStory +
                ", progress='" + progress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(idTask, task.idTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask);
    }
}
