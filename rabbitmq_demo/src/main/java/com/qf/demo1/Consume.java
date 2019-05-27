package com.qf.demo1;

import com.qf.util.ConnUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

/**
 * @vervion 1.0
 * @date 2019/05/24 16:47
 * @user Jack-Hunting
 */
public class Consume {

    public static void main(String[] args) throws Exception {
//        创建一个固定大小为5的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        连接rabbitmq
        Connection conn = ConnUtil.getConn();
//        创建通道
        Channel channel = conn.createChannel();
//        创建队列  -->实际开发中，在提供者方和消费者方都创建一个队列名和参数都相同的队列
        channel.queueDeclare("my_queue_1",false,false,false,null);
//        监听队列
        channel.basicConsume("my_queue_1",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                调用线程池处理事务
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            当队列中有消息时，回调这个方法
                            String str = new String(body,"utf-8");
                            System.out.println("接收到队列中方法："+str);
                            Thread.sleep(3000);
                        } catch (UnsupportedEncodingException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
}
