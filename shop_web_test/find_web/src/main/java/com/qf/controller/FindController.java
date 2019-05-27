package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.service.IFindService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @vervion 1.0
 * @date 2019/05/22 16:38
 * @user Jack-Hunting
 */
@Controller
@RequestMapping("/find")
public class FindController {

    @Reference
    private IFindService findService;

    @RequestMapping("/getGoodsListByKeyword")
    public String getGoodsListByKeyword(String keyword, Model model, Page page,Integer currentPage){
        if (currentPage == null){
            page.setCurrentPage(1);
        }else {
            page.setCurrentPage(currentPage);
        }

        page = findService.getGoodsByKeyword(keyword, page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "goodslist";
    }


}
