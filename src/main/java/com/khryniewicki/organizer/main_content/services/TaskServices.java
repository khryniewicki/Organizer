package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.model.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServices {
    private final SprintService sprintService;
    private final TaskRepository taskRepository;
    private final ProgressServices progressServices;
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
        if (updatedTask.getProgress()!=null) task.setProgress(updatedTask.getProgress());
        task.setPriority(updatedTask.getPriority());

        task.setSprint(updatedTask.getSprint());
        task.setStoryPoints(updatedTask.getStoryPoints());
        task.setUsers(updatedTask.getUsers());
        taskRepository.save(task);
    }


    public List<Task> taskListBySprintId (Long id){
       return taskRepository.findAllBySprint(sprintService.findById(id));
    }
    public void deleteTask(Long id) {
       taskRepository.deleteById(id);
    }
}
