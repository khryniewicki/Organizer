package com.khryniewicki.organizer.main_content.controllers.RestControllers;

import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.function.Function;

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




//    @Bean
//    public Queue autoDeleteQueue1(Long userId)    {
//        return new Queue("taskInformation." + userId);
//    }


//    @Bean
//    @Scope(value = "prototype")
//    public Queue autoDeleteQueue1(Long userId)    {
//        return new Queue("taskInformation." + userId);
//    }

//    @Bean
//    public Function<Long, Queue> queueFactory() {
//        return userId -> autoDeleteQueue1(userId);
//    }





}






