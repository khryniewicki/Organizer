package com.khryniewicki.organizer.main_content.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
