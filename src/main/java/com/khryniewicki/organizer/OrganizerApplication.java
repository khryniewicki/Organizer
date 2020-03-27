package com.khryniewicki.organizer;

import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class OrganizerApplication  {

    public static void main(String[] args)  {
        SpringApplication.run(OrganizerApplication.class, args);
    }
}