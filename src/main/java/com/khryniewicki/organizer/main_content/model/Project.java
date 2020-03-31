package com.khryniewicki.organizer.main_content.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.services.ProjectService;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private List<Task> tasks;
    @ManyToMany()
    @JsonIgnore
    private List<User> users;
    private String avatar;

    public Project(String name, String description,String avatar) {
        this.name=name;
        this.description=description;
        this.admin= UtillClass.getLoggedInUser().getEmail();
        this.avatar= avatar;
    }

    public Project(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", admin='" + admin + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
