package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.model.Href;
import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.services.TaskServices;
import com.khryniewicki.organizer.main_content.services.HrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class UpdateCard {
        private final TaskServices taskServices;
        private final HrefService hrefService;
    @GetMapping("/task/{id}/{progress}")
    public void updateCard(@PathVariable("id") Long id,@PathVariable("progress") String progress){
        Task task = taskServices.findTask(id);
        task.setProgress(progress);
        taskServices.saveTask(task); }

    @GetMapping(value = "/dashboardview/{href}" )
    public void updateLinkToDashboard(@PathVariable("href") Long href){
            hrefService.saveOrUpdate(href); }

}
