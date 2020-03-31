package com.khryniewicki.organizer.main_content.model;

import com.khryniewicki.organizer.registration_login_logout.DTO.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String nick;

    @NotNull
    private String email;
    @NotNull
    private String password;

    @ManyToMany (mappedBy = "users")
    private List<Project> projects;

    @OneToOne (mappedBy = "user")
    private Task task;

    private String href;

    @Enumerated
    public Roles role;

    public User(String name, String surname,  @NotNull String email, @NotNull String password,  @NotNull String nick, Roles role) {
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(List<Project> projects) {
        this.projects = projects;
    }

    public User(Task task) {
        this.task = task;
    }

    public User(String href) {
        this.href=href;
    }

}
