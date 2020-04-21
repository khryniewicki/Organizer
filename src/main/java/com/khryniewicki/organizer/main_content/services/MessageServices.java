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


    public Message saveAndGetReadyMessage(MessageDTO messageDTO,String update) {
        Message message = createMessageEntity(messageDTO, update);
        messageRepository.save(message);

        return message;
    }

    public String defineMessageText(ModyficationType modyficationType,String taskName, String projectName, String userEmail,String progress) {
        String message = "";
        switch (modyficationType) {
            case UPDATE:
                message= createUpdateMessage(taskName, projectName, userEmail);
                break;
            case UPDATE_PROGRESS:
                message= createUpdateProgressMessage(taskName, projectName, userEmail,progress);
                break;
            default:
                throw new IllegalArgumentException("Nie ma takiejmodyfikacji");
        }
        return message;

    }

    public Message createMessageEntity(MessageDTO messageDTO, String update) {


        Long taskId = messageDTO.getTaskId();
        Long userId=messageDTO.getUserId();

        Task task = taskServices.findTask(taskId);
        String taskName = task.getName();
        String progress=task.getProgress();
        Long projectId = task.getProject().getId();
        String projectName = task.getProject().getName();
        String userEmail = userService.findUserById(userId).getEmail();

        String innerMessageText = defineMessageText(ModyficationType.valueOf(update),taskName, projectName, userEmail,progress);


        return new Message(taskId, projectId, innerMessageText, userId);

    }

    public String createUpdateMessage(String taskName, String projectName, String userEmail) {
        return String.format("Użytkownik %s zmodyfikował w projekcie '%s' zadanie '%s'",userEmail,projectName,taskName);
    }
    public String createUpdateProgressMessage(String taskName, String projectName, String userEmail,String progress) {
        return String.format("Użytkownik %s zmienił w projekcie '%s' w zadaniu '%s' status na '%s'",userEmail,projectName,taskName,progress);
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
