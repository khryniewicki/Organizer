package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.repositories.ProjectRepository;
import com.khryniewicki.organizer.registration_login_logout.DTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projektRepository;

    public void saveProject (Project project){
        projektRepository.save(project);
    }

    public Project findProject (Long id_project){
        Optional<Project> projekt = projektRepository.findById(id_project);
        Project project1 = new Project();
        if ( projekt.isPresent()){
          project1 = projekt.get();
        }
       return project1;
    }
    public Project findProjectByName (String name){
        Optional<Project> projekt = projektRepository.findByName(name);
        Project project1 = new Project();
        if ( projekt.isPresent()){
            project1 = projekt.get();
        }
        return project1;
    }
    public List<Project> getAllProjekts(){
       return projektRepository.findAll();
    }

    public void createProject(ProjectDTO projectDTO){
        Project project = new Project(projectDTO.getName(),projectDTO.getDescription());
        projektRepository.save(project);
    }

    public static String CreateiconRandomViewer()  {
        String[] pathnames;

        File f = new File("/home/konrad/IdeaProjects/github/organizer/src/main/resources/static/img/icons");

        pathnames = f.list();
        int length = pathnames.length;
        if (length!=0){
            Random random_generator = new Random();
            int number = random_generator.nextInt(length) ;
            return "icons/"+pathnames[number];
        }
        else
        return "steam.png";
    }
}
