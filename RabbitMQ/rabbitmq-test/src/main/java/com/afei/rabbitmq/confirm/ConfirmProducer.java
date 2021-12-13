package com.afei.rabbitmq.confirm;


import com.afei.rabbitmq.util.ConstantProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
public class  ConfirmProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/ttl/confirmMessage/{msg}")
    public String sendDelayMsg(@PathVariable("msg") String msg) {

        rabbitTemplate.convertAndSend(ConstantProperties.CONFIRM_EXCHANGE_NAME,ConstantProperties.CONFIRM_ROUTING_KEY,
                "发布确认消息: " + msg, new CorrelationData("确认消息id！"));
        log.info("交换机：发布确认消息: " + msg);
        return "发送消息成功！";
    }

}
