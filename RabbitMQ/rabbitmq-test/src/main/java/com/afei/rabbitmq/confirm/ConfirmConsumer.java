package com.afei.rabbitmq.confirm;

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
public class ConfirmConsumer {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = ConstantProperties.CONFIRM_EXCHANGE_NAME,
                    durable = "false", type = "direct"),
            key = { ConstantProperties.CONFIRM_ROUTING_KEY },
            value = @Queue(value = ConstantProperties.CONFIRM_QUEUE_NAME, durable = "false")
    ))
    public void getDelayMsg(Message msg, Channel channel, String strMsg) throws UnsupportedEncodingException {
        log.info("消费者：收到确认消息=>" + strMsg);
    }

}
