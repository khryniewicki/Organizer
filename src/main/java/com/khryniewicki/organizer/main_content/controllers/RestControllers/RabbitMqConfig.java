package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConfig {

    private static final DirectExchange directExchange = new DirectExchange("organizer");

    public static DirectExchange getDirectExchange() {
        return directExchange;
    }

    @Bean
    public Queue autoDeleteQueue1()    {
        return new Queue("taskOrganizer.*");
    }
    @Bean
    public Binding binding1a(Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(directExchange)
                .with(autoDeleteQueue1.getActualName());
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }


}






