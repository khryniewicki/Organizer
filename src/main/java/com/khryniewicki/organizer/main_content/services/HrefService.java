package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Href;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.model.repositories.HrefRepository;
import com.khryniewicki.organizer.main_content.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class HrefService {
        private final HrefRepository hrefRepository;
        private final ProjectService projectService;
    public void saveHref(Long id){
        Project project = projectService.findProject(id);
        hrefRepository.save(new Href(project.getId()));
    }

    public Long getLast(){
        List<Href> all = hrefRepository.findAll();
        if (all.isEmpty()) all.add(new Href(1L));
        return 1L;
    }
    public Project getLastProject(){
        List<Href> all = hrefRepository.findAll();
        if (all.isEmpty()) all.add(new Href(1L));
        return projectService.findProject(1L);

    }

}


