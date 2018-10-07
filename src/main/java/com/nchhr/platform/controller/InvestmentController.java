package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invest")

//项目投资控制器，包括展示项目投资人，进行投资等
public class InvestmentController {

    @RequestMapping("/detail")
    public String investList() {

        return "investList";
    }
}
