package com.khryniewicki.organizer.main_content.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Project {
    @Id
    @Column(unique = true)
    private String name;
    private String description;
    private String admin;

}
