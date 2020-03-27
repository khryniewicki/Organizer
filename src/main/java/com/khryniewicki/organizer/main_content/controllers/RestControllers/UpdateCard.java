package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.services.TaskServices;
import com.khryniewicki.organizer.main_content.services.HrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class UpdateCard {
        private final TaskServices taskServices;
        private final HrefService hrefService;
    @GetMapping("/task/{id}/{progress}")
    public String updateCard(@PathVariable("id") Long id,@PathVariable("progress") String progress){
        Task tmptask = taskServices.findTask(id);
        tmptask.setProgress(progress);
        taskServices.updateTask(tmptask,id);
        return "Challange accepted";
    }
    @GetMapping("/dashboardview/{href}")
    public String updateDashboard(@PathVariable("href") Long href){
            hrefService.saveHref(href);
        return "Challange accepted";
    }

}
