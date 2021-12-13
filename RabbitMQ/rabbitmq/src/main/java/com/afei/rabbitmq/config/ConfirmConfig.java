package com.afei.rabbitmq.config;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *  发布确认的回调函数
 */
// @Configuration    -> 配置后在发送消息的时候就需要CorrelationData
public class ConfirmConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (StringUtils.isEmpty(correlationData.getId())) {
            return;
        }
        if (ack == true) {
            System.out.println("=========================>" + "接收成功！");
        } else {
            System.out.println("=========================>" + "接收失败, 失败原因：" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息："+ new String(message.getBody()) + "，回退原因：" + replyText + "，回退到：" + exchange + "交换机，路由key：" + routingKey);
    }
}
