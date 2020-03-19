package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projektRepository;

    public void saveProject (Project project){
        projektRepository.save(project);
    }

    public Project findProject (String name){
        Optional<Project> projekt = projektRepository.findById(name);
        Project project1 = new Project();
        if ( projekt.isPresent()){
          project1 = projekt.get();
        }
       return project1;
    }

    public List<Project> getAllProjekts(){
       return projektRepository.findAll();
    }
}
