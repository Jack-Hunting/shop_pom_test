package com.qf.listener;

import com.qf.controller.ItemController;
import com.qf.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @vervion 1.0
 * @date 2019/05/25 10:06
 * @user Jack-Hunting
 */
@Component
public class RabbitMQListener {

    @Autowired
    private Configuration configuration;

    @RabbitListener(queues = "item_queue")
    public void GoodsMessHandler(Goods goods){
        //        静态页面的输出路径
        String path = ItemController.class.getResource("/static/html/").getPath() + goods.getId() + ".html";

        try (
                Writer out = new FileWriter(path);
        )
        {
//              商品详情页的模板
            Template template = configuration.getTemplate("goods.ftl");
            Map map = new HashMap();
            String[] imgs = goods.getGimg().split("\\|");
            map.put("goods",goods);
            map.put("gimages",imgs);

            template.process(map,out);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
