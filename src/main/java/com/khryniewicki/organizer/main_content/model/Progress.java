package com.khryniewicki.organizer.main_content.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
public class Progress {
    @Id
    private String name;
    public Progress() {
    }

}
