package com.khryniewicki.organizer.registration_login_logout.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String avatar;
    private String admin;
}
