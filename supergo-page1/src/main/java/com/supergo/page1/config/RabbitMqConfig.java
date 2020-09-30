package com.supergo.page1.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    /**
     * 直连方式
     * @return
     */
    @Bean
    public Queue kkbQueue() {
        //参数一：队列的名称  参数二：是否持久化
        return new Queue("kkb1",true);
    }

    @Bean
    public Queue huikeQueue() {
        //参数一：队列的名称  参数二：是否持久化
        return new Queue("huike",true);
    }

    //扇形交换机
    @Bean
    public FanoutExchange helloExchange() {
        return new FanoutExchange("hello");
    }

    /**
     * 扇形交换机绑定关系
     * @param kkbQueue
     * @param helloExchange
     * @return
     */
    @Bean
    public Binding bindingKkbQueue(Queue kkbQueue, Exchange helloExchange) {
        return BindingBuilder
                .bind(kkbQueue)
                .to(helloExchange)
                .with("")
                .noargs();
    }

    //绑定交换机
    @Bean
    public Binding bindingHuikeQueue(Queue huikeQueue, Exchange helloExchange) {
        return BindingBuilder
                .bind(huikeQueue)
                .to(helloExchange)
                .with("")
                .noargs();
    }
}
