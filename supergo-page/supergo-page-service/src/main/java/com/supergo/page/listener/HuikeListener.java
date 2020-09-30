package com.supergo.page.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 慧科队列监听器
 */
@RabbitListener(queues = {"huike"})
@Component
public class HuikeListener {

    @RabbitHandler
    public void messageHandler(String message) {
        //接收到消息，并处理消息
        System.out.println("huike接收到消息111:  " + message);
    }
}
