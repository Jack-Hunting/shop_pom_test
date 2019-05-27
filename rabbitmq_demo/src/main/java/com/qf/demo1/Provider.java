package com.qf.demo1;

import com.qf.util.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * @vervion 1.0
 * @date 2019/05/24 16:29
 * @user Jack-Hunting
 */

public class Provider {

    public static void main(String[] args) throws Exception {
//        获得连接对象
        Connection conn = ConnUtil.getConn();
//        创建通道
        Channel channel = conn.createChannel();
//        通过通道创建一个队列
        channel.queueDeclare("my_queue_1",false,false,false,null);
        /* 给队列发送消息 */
        String mess = new String("Hello rabbitMQ!   -->2");
        channel.basicPublish("","my_queue_1",null, mess.getBytes("utf-8"));

        conn.close();
        System.out.println("当前提供者执行完成！");
    }
}
