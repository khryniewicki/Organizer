package com.khryniewicki.organizer.main_content.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDTO {
    private Long id;
    @NotNull
    @Size(min=2, max=50)
    private String name;
    @Size(max=50)
    private String description;
    @NotNull
    @NotBlank
    private String avatar;
    private String admin;
}
