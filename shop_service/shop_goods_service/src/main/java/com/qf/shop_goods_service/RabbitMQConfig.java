package com.qf.shop_goods_service;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @vervion 1.0
 * @date 2019/05/25 09:42
 * @user Jack-Hunting
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 创建搜索队列
     * @return
     */
    @Bean
    public Queue getQueue1(){
        return new Queue("find_queue");
    }

    /**
     * 创建详情队列
     * @return
     */
    @Bean
    public Queue getQueue2(){
        return new Queue("item_queue");
    }

    /**
     * 获得广播消息类型的交换机
     */
    @Bean
    public FanoutExchange getExchange(){
        return (FanoutExchange) ExchangeBuilder.fanoutExchange("goods_exchange").build();
    }

    /**
     * 将创建好的两个队列和交换机分别进行绑定
     */
    @Bean
    public Binding getBinding1(Queue getQueue1,FanoutExchange getExchange){
        return BindingBuilder.bind(getQueue1).to(getExchange);
    }

    @Bean
    public Binding getBinding2(Queue getQueue2,FanoutExchange getExchange){
        return BindingBuilder.bind(getQueue2).to(getExchange);
    }


}
