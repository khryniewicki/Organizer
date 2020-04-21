package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.model.Href;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.repositories.HrefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HrefService {
    private final HrefRepository hrefRepository;
    private final ProjectService projectService;


    public void saveOrUpdate(Long id) {
        Optional<Href> byId_href = hrefRepository.findByUsername(UtillClass.getLoggedInUser().getEmail());
        if (byId_href.isPresent()) {
            Href hrefToUpdate = byId_href.get();
            hrefToUpdate.setIdProject(id);
            hrefRepository.save(hrefToUpdate);
        } else hrefRepository.save(new Href(id, UtillClass.getLoggedInUser().getEmail()));
    }

    public Long getLast() {
        String EmailBelongsToLoggedUser = UtillClass.getLoggedInUser().getEmail();
        if (!EmailBelongsToLoggedUser.equals(null)) {
            Optional<Href> byUsername = hrefRepository.findByUsername(EmailBelongsToLoggedUser);
            if (byUsername.isPresent())
                return byUsername.get().getIdProject();
            else return new Href().getIdProject();
        }
        throw new NullPointerException("Użytkownik nie jest zalogowany");
    }

    public Project getLastProject() {
        Optional<Href> byUsername = hrefRepository.findByUsername(UtillClass.getLoggedInUser().getEmail());
        if (byUsername.isPresent())
            return projectService.findProject(byUsername.get().getIdProject());
        else return new Project();
    }

}


