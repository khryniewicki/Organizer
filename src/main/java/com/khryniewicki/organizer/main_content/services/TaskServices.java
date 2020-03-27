package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Href;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.model.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServices {
    private final SprintService sprintService;
    private final TaskRepository taskRepository;
    private final ProgressServices progressServices;
    private final ProjectService projectService;
    private final HrefService hrefService;

    public void saveTask(Task task) {
        task.setProgress(progressServices.findAllProgress().get(0).getName());
        taskRepository.save(task);
    }

    public Task findTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak takiego zadania"));
    }

    public void updateTask(Task updatedTask, Long id) {
        Task task = findTask(id);
        task.setDescription(updatedTask.getDescription());
        task.setName(updatedTask.getName());
        if (updatedTask.getProgress() != null) task.setProgress(updatedTask.getProgress());
        task.setPriority(updatedTask.getPriority());
        task.setTypeOfStory(updatedTask.getTypeOfStory());
        task.setProject(updatedTask.getProject());
        task.setSprint(updatedTask.getSprint());
        task.setStoryPoints(updatedTask.getStoryPoints());
        task.setUser(updatedTask.getUser());
        taskRepository.save(task);
    }


    public List<Task> taskListBySprintId(Long id) {
        return taskRepository.findAllBySprint(sprintService.findById(id)).orElseThrow(() -> new IllegalArgumentException("brak zadan"));
    }

    public List<Task> taskListByProjectId() {
        Long last = hrefService.getLast();
        if (last!=null){
        Optional<List<Task>> AllTasksByProjectName = taskRepository.findAllByProjectId(last);
        return AllTasksByProjectName.orElse(new ArrayList<Task>());}
        else return new ArrayList<Task>();
    }
    public List<Task> taskListByProjectId(Long id) {
        Optional<List<Task>> AllTasksByProjectName = taskRepository.findAllByProjectId(id);
            return AllTasksByProjectName.orElse(new ArrayList<Task>());

    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
