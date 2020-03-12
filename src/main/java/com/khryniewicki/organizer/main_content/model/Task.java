package com.khryniewicki.organizer.main_content.model;

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
    private Sprint sprint;

    private Priority priority;
    private Long storyPoints;
    private String progress;

    @ManyToMany
    private List<User> users;
}
