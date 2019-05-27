package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @vervion 1.0
 * @date 2019/05/20 19:30
 * @user Jack-Hunting
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @Value("${img.server}")
    private String imgpath;

    @RequestMapping("/list")
    public String getGoodsList(Model model){
        List<Goods> list = goodsService.queryList();

        model.addAttribute("goodslist",list);
        model.addAttribute("imgpath", imgpath);
        return "goodslist";
    }

    @RequestMapping("/add")
    public String addGoods(Goods goods){
        goodsService.add(goods);
        return "redirect:/goods/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer gid){
        goodsService.delete(gid);
        return "redirect:/goods/list";
    }

    @RequestMapping("/getGoodsInfo")
    public String getGoodsInfo(Integer gid,Model model){
        Goods goods = goodsService.getInfoById(gid);
        model.addAttribute("goods",goods);
        model.addAttribute("imgpath", imgpath);
        return "updateGoods";
    }

    @RequestMapping("/update")
    public String update(Goods goods){
        goodsService.update(goods);
        return "redirect:/goods/list";
    }
}
