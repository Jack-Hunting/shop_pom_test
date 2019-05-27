package com.qf.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @vervion 1.0
 * @date 2019/05/24 16:51
 * @user Jack-Hunting
 */
public class ConnUtil {

    private static ConnectionFactory connfac;

    static {
        connfac = new ConnectionFactory();
        connfac.setHost("152.136.157.169");
        connfac.setUsername("guest");
        connfac.setPassword("guest");
        connfac.setVirtualHost("/");
        connfac.setPort(5672);
    }

    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = connfac.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
