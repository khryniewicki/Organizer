package com.khryniewicki.organizer.main_content.model;

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
    private String login;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirm_password;
    @NotNull
    private String nick;
    @ManyToMany (mappedBy = "users")
    private List<Task> tasks;
}
