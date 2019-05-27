package com.qf.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @vervion 1.0
 * @date 2019/05/23 20:40
 * @user Jack-Hunting
 */
public class HttpUtil {

    public static String sendGet(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000*5);
            conn.setReadTimeout(1000*5);

//            连接服务器
            conn.connect();
            //获得服务器的响应
            InputStream in = conn.getInputStream();
            String result = null;

            byte[] buffer = new byte[1024 * 10];
            int len = 0;
            //准备一个内存输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            while((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }

            //将内存流的中的数据转换成String字符串
            result = new String(out.toByteArray());

            in.close();
            out.close();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
