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
    public void saveHref(String href){
        hrefRepository.save(new Href(href));
    }

    public String getLast(){
        List<Href> all = hrefRepository.findAll();
       return all.get(all.size() - 1).getName();
    }
    public Project getLastProject(){
        List<Href> all = hrefRepository.findAll();
        return projectService.findProject(all.get(all.size() - 1).getName());

    }

}


