package com.afei.rabbitmq.delay;


import com.afei.rabbitmq.util.ConstantProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
public class Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/ttl/delayMessage/{time}")
    public String sendDelayMsg(@PathVariable("time") Integer time) {

        rabbitTemplate.convertAndSend(ConstantProperties.DELAY_EXCHANGE_NAME, ConstantProperties.DELAY_ROUTING_KEY,
                "发送延迟消息:" + new Date().toString() + "，延迟时间是："  + time + "sm",
                msg -> {
                    msg.getMessageProperties().setDelay(time);
                    return msg;
                });
        log.info("发送延迟消息:" + new Date().toString() + "，延迟时间是："  + time + "sm");
        return "发送消息成功！";
    }

}
