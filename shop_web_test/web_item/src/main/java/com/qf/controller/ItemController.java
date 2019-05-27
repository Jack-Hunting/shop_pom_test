package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @vervion 1.0
 * @date 2019/05/23 20:10
 * @user Jack-Hunting
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private Configuration configuration;

    @Reference
    private IGoodsService goodsService;

    /**
     * 添加商品时调用这个方法生成该商品的静态页面
     * @param gid
     * @return
     */
    @RequestMapping("/createhtml")
    @ResponseBody
    public String createhtml(Integer gid){
//        静态页面的输出路径
        String path = ItemController.class.getResource("/static/html/").getPath() + gid + ".html";

        try (
                Writer out = new FileWriter(path);
                )
        {
//              商品详情页的模板
            Template template = configuration.getTemplate("goods.ftl");
//            获得商品对应的数据
            Goods goods = goodsService.getInfoById(gid);
            Map map = new HashMap();
            String[] imgs = goods.getGimg().split("\\|");
            map.put("goods",goods);
            map.put("gimages",imgs);

            template.process(map,out);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return "bingo!";
    }
}
