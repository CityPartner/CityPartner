package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funds")

//项目资金控制器，包括资金流入（项目收入）、资金流出（项目支出）、资金分红
public class FundsController {

    //项目支出
    @RequestMapping("/income")
    public String incomme() {

        return "";//没有静态页面
    }

    //项目收入
    @RequestMapping("/expenses")
    public String expenses() {

        return "";//没有静态页面
    }

    //项目分红
    @RequestMapping("/dividend")
    public String dividend() {

        return "";//没有静态页面
    }

}
