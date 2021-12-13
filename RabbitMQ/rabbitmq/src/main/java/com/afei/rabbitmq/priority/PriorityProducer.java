package com.afei.rabbitmq.priority;

import com.afei.rabbitmq.util.ConstantProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriorityProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/ttl/priorityMessage")
    public String priorityMessage() {
        // 优先级越大越优先, 发送消息到丢列的时候根据消息的优先级重新排序
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            rabbitTemplate.convertAndSend(ConstantProperties.PRIORITY_EXCHANGE_NAME, ConstantProperties.PRIORITY_ROUTING_KEY,
                    "info" + i,
                    msg -> {
                        if (finalI == 5) {
                            msg.getMessageProperties().setPriority(5);
                        }
                        return msg;
                    });
        }
        return "发送消息成功！";
    }

}
