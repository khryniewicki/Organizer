package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.khryniewicki.organizer.main_content.config.RabbitMqConfig.getDirectExchange;

@RestController
@RequiredArgsConstructor
public class createQueue {

    private final  AmqpAdmin amqpAdmin;

    @GetMapping("/createQueue")
    public String create(@RequestParam(value = "userId") String userId) {
        String queueName = "taskInformation." + userId;

        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
        Binding binding=BindingBuilder.bind(queue).to(getDirectExchange()).with(queueName);
        amqpAdmin.declareBinding(binding);

        return "Queue created" + userId;
    }


}
