package com.khryniewicki.organizer.registration_login_logout.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    @NotNull
    @NotEmpty
    private String firstName;
    @NotBlank

    @NotNull
    @NotEmpty
    private String secondName;

    @NotBlank
    @NotNull
    @NotEmpty
    private String nick;
    @NotBlank
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    private String matchingPassword;

    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    private String email;

    private Roles roles;
}
