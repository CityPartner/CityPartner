package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



//测试用 请勿使用该控制器
@Controller
@RequestMapping("")
public class MainController {

    @RequestMapping(value = "/{resourseName}")
    public ModelAndView index(@PathVariable("resourseName") String resourseName) {
        ModelAndView modelAndView = new ModelAndView(resourseName);
        return modelAndView;
    }
}
