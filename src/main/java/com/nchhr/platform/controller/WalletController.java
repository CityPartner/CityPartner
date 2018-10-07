package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")

//项目钱包控制器，包括我的收入详情、收入提现
public class WalletController {

    //钱包——收入详情
    @RequestMapping("/income")
    public String income() {

        return "income";
    }

    //钱包——盈利提现
    @RequestMapping("/withdraw")
    public String withdraw() {

        return "withdraw";
    }

    //钱包——项目发起人处理提现
    @RequestMapping("/dealwith")
    public String dealwith() {

        return "dealwith";
    }
}
