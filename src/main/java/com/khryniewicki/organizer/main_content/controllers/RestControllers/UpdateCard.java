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

    @GetMapping("/task/{taskId}/{progress}")
    public void updateCardProgress(@PathVariable("taskId") Long taskId, @PathVariable("progress") String progress) {
        Task task = taskServices.findTask(taskId);
        task.setProgress(progress);
        taskServices.saveTask(task);
    }

    @GetMapping(value = "/dashboardview/{href}")
    public String updateLinkToDashboard(@PathVariable("href") String href) {
        Long aLong = Long.valueOf(href);
        hrefService.saveOrUpdate(aLong);
        return "Ok";
    }

}
