package com.supergo.page.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {

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

    @Bean
    public TopicExchange kkbTopicExchange() {
        return new TopicExchange("kkbTopic");
    }

    /**
     * 主题交换机绑定键
     * @param kkbQueue
     * @param kkbTopicExchange
     * @return
     */
    @Bean
    public Binding bindingKkbQueue(Queue kkbQueue, Exchange kkbTopicExchange) {
        return BindingBuilder
                .bind(kkbQueue)
                .to(kkbTopicExchange)
                .with("kkb.a")
                .noargs();
    }

    @Bean
    public Binding bindingHuikeQueue(Queue kkbQueue, Exchange kkbTopicExchange) {
        return BindingBuilder
                .bind(kkbQueue)
                .to(kkbTopicExchange)
                .with("kkb.*")
                .noargs();
    }
}
