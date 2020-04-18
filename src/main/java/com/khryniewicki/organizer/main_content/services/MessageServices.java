package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.DTO.MessageDTO;
import com.khryniewicki.organizer.main_content.model.Message;
import com.khryniewicki.organizer.main_content.model.Task;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.model.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageServices {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final TaskServices taskServices;
    private final ProjectService projectService;


    public List<Message> getLast5MessagesForActiveUser(Long userId) {

        List<Long> allProjectIdsForUser = projectService.findAllProjectForUser(userId).stream()
                .map(project -> project.getId())
                .collect(Collectors.toList());

        List<Message> allMessages = messageRepository.findAll();
        return allMessages.stream()
                .filter(message -> (allProjectIdsForUser.stream().anyMatch(projectId -> message.getProjectId() == projectId)))
                .sorted(Comparator.comparing(Message::getMessageId).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }


    public Message saveAndGetReadyMessage(MessageDTO messageDTO) {
        String innerMessageText = defineMessageText(ModyficationType.UPDATE);
        Message message = createMessageEntity(messageDTO, innerMessageText);
        messageRepository.save(message);

        return message;
    }

    public String defineMessageText(ModyficationType modyficationType) {
        String aboutTask = "";
        switch (modyficationType) {
            case UPDATE:
                aboutTask = "' zadanie '";
                break;
            case UPDATE_PROGRESS:
                aboutTask = "' status zadania '";
                break;
            default:
                throw new IllegalArgumentException("Nie ma takiejmodyfikacji");
        }
        return aboutTask;

    }

    public Message createMessageEntity(MessageDTO messageDTO, String innerMessageText) {
        Long taskId = messageDTO.getTaskId();
        Long userId=messageDTO.getUserId();

        Task task = taskServices.findTask(taskId);
        String taskName = task.getName();
        Long projectId = task.getProject().getId();
        String projectName = task.getProject().getName();
        String userEmail = userService.findUserById(userId).getEmail();

        String message = createStringMessage(innerMessageText, taskName, projectName, userEmail);

        return new Message(taskId, projectId, message, userId);

    }

    public String createStringMessage(String innerMessageText, String taskName, String projectName, String userEmail) {
        return String.format("Użytkownik %s zmodyfikował w projekcie '%s' %s '%s'",userEmail,projectName,innerMessageText,taskName);
    }

    public Set<Long> getAssignedUsersToProject(MessageDTO messageDTO) {
        Long taskId = messageDTO.getTaskId();
        Long projectId = taskServices.getProjectIdFromTask(taskId);
        Set<Long> usersAssignedToProject = projectService.findProject(projectId).getUsers().stream()
                .map(User::getIdUser)
                .collect(Collectors.toSet());
        return usersAssignedToProject;
    }
}
