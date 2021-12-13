package com.afei.rabbitmq.config;


import com.afei.rabbitmq.util.ConstantProperties;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PriorityConfig {

    @Bean("priorityExchange")
    public DirectExchange priorityExchange() {
        return new DirectExchange(ConstantProperties.PRIORITY_EXCHANGE_NAME, false, false, null);
    }

    @Bean("priorityQueue")
    public Queue priorityQueue() {
        // Map args = new HashMap();
        return QueueBuilder.nonDurable(ConstantProperties.PRIORITY_QUEUE_NAME).withArgument("x-max-priority", 10).build();
    }

    @Bean
    public Binding priorityBinding(@Qualifier("priorityExchange") DirectExchange priorityExchange,
                                   @Qualifier("priorityQueue") Queue priorityQueue) {

        return BindingBuilder.bind(priorityQueue).to(priorityExchange).with(ConstantProperties.PRIORITY_ROUTING_KEY);
    }

}
