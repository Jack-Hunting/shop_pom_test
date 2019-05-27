package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @vervion 1.0
 * @date 2019/05/20 19:00
 * @user Jack-Hunting
 */
@Controller
@RequestMapping("/back")
public class BackToController {

    @RequestMapping("/{htmlpage}")
    public String tohtml(@PathVariable String htmlpage){
        return htmlpage;
    }
}
