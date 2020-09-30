package com.supergo.page.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 队列监听器(接收消息)
 */
@RabbitListener(queues = {"kkb1"})
@Component
public class KkbListener {

    @RabbitHandler
    public void messageHandler(String message) {
        //接收到消息，并处理消息
        System.out.println("接收到消息111:  " + message);
    }
}
