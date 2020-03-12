package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.model.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskServices {

    private final TaskRepository taskRepository;

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public Task findTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak takiego zadania"));
    }

    public void updateTask(Task updatedTask, Long id) {
        Task task = findTask(id);
        task.setDescription(updatedTask.getDescription());
        task.setName(task.getName());
        task.setPriority(updatedTask.getPriority());
        task.setProgress(updatedTask.getProgress());
        task.setSprint(updatedTask.getSprint());
        task.setStoryPoints(updatedTask.getStoryPoints());
        task.setUsers(updatedTask.getUsers());
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
       taskRepository.deleteById(id);
    }
}
