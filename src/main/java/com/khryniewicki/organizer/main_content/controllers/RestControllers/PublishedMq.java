package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.DTO.MessageDTO;
import com.khryniewicki.organizer.main_content.model.Message;
import com.khryniewicki.organizer.main_content.services.MessageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class PublishedMq {

    private final MessageServices messageServices;
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;


    @GetMapping("/sendtaskInformation/{userId}")
    public void saveMessageAndSendToAssignedUsers( @PathVariable(value = "userId") String userId,@RequestParam(value = "taskId") String taskId , @RequestParam(value = "update") String update) {

        Long taskIdLong = Long.parseLong(taskId);
        Long userIdLong = Long.parseLong(userId);
        MessageDTO messageDTO = new MessageDTO(taskIdLong, userIdLong);


        Message messageReadyToSend = messageServices.saveAndGetReadyMessage(messageDTO,update);

        Set<Long> assignedUsersIdToProject = messageServices.getAssignedUsersToProject(messageDTO);

        assignedUsersIdToProject.forEach(Id -> rabbitTemplate.convertAndSend("taskInformation." + Id, messageReadyToSend.getMessage()));


    }

    @GetMapping("/newInformationCounter/{userId}")
    public Integer getNewInformationCounter(@PathVariable(value = "userId") String userId) {

        String queueName = "taskInformation." + userId;

        Properties props = amqpAdmin.getQueueProperties(queueName);

        Integer messageCount = Integer.parseInt(props.get("QUEUE_MESSAGE_COUNT").toString());

        return messageCount;
    }
}
