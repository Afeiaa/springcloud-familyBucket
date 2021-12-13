package com.afei.rabbitmq.delay;

import com.afei.rabbitmq.util.ConstantProperties;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
@Component     // 交给spring管理才能进行监听
public class Consumer {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = ConstantProperties.DELAY_EXCHANGE_NAME,
                    durable = "false", delayed = "true"),
            key = {ConstantProperties.DELAY_ROUTING_KEY},
            value = @Queue(value = ConstantProperties.DELAY_QUEUE_NAME, durable = "false")
    ))
    public void getDelayMsg(Message msg, Channel channel, String strMsg) throws UnsupportedEncodingException {
        log.info(new Date().toString() + "：收到消息 -> " + new String(msg.getBody(), "utf-8"));
        log.info("================" + strMsg);
    }

}
