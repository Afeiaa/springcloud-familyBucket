package com.afei.rabbitmq.priority;


import com.afei.rabbitmq.util.ConstantProperties;
import com.rabbitmq.client.Channel;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PriorityConsumer {

//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(value = ConstantProperties.PRIORITY_EXCHANGE_NAME, type = "direct", durable = "false"),
//            key = { ConstantProperties.PRIORITY_ROUTING_KEY},
//            value = @Queue(value = ConstantProperties.PRIORITY_QUEUE_NAME, durable = "false",)
//    ))
    @RabbitListener(queues = ConstantProperties.PRIORITY_QUEUE_NAME)
    public void rePriorityMessage(Message msg, Channel channel, String str) {
        System.out.println("==================>收到消息：" + str);
    }
}
