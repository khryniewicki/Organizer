package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.function.Function;

import static com.khryniewicki.organizer.main_content.controllers.RestControllers.RabbitMqConfig.getDirectExchange;

@RestController
@RequiredArgsConstructor
public class createQueue {

    private final RabbitMqConfig rabbitMqConfig;
    private final  AmqpAdmin amqpAdmin;

    @GetMapping("/createQueue")
    public String create(@RequestParam(value = "userId") String userId) {
        Long userIDLONG = Long.parseLong(userId);
        String queueName = "taskInformation." + userId;


        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
        Binding binding=BindingBuilder.bind(queue).to(getDirectExchange()).with(queueName);
        amqpAdmin.declareBinding(binding);

        return "Queue created" + userId;
    }


}
