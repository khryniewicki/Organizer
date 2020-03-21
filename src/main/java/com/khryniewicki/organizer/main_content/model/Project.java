package com.khryniewicki.organizer.main_content.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @Column(unique = true)
    private String name;
    private String description;
    private String admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Task> tasks;
}
