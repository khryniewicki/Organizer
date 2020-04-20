package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.config.RabbitMqConfig;
import com.khryniewicki.organizer.main_content.services.RabbitService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.khryniewicki.organizer.main_content.config.RabbitMqConfig.getDirectExchange;

@RestController
@RequiredArgsConstructor
public class createQueue {

    private final RabbitService rabbitService;

    @GetMapping("/createQueue")
    public String create(@RequestParam(value = "userId") String userId) {

        rabbitService.createQueue(userId);
        return "Queue created" + userId;
    }


}
