package com.khryniewicki.organizer.registration_login_logout.DTO;

import com.khryniewicki.organizer.registration_login_logout.validator.PasswordMatches;
import com.khryniewicki.organizer.registration_login_logout.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDTO {
    @NotBlank
    @NotNull
    @Size( max=30)
    private String firstName;

    @NotBlank
    @NotNull
    @Size( max=30)
    private String secondName;

    @NotBlank
    @NotNull
    @Size( max=30)
    private String nick;

    @ValidPassword
    @NotNull
    private String password;

    @NotNull
    private String matchingPassword;

    @NotBlank
    @NotNull
    @Email
    @Size(max=50)
    @Column(unique = true)
    private String email;

    private Roles roles;
}
