package com.nchhr.platform.controller;

import com.nchhr.platform.entity.FixedOverheadEntity;
import com.nchhr.platform.entity.IncomeEntity;
import com.nchhr.platform.service.FundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/funds")

//项目资金控制器，包括资金流入（项目收入）、资金流出（项目支出）、资金分红
public class FundsController {

    @Autowired
    FundsService fundsService;

    //项目收入
    @RequestMapping("/income")
    public String income() {
        List<IncomeEntity> incomeEntity = fundsService.income();


        return "";//没有静态页面
    }

    //项目支出
    @RequestMapping("/expenses")
    public String expenses() {
        List<FixedOverheadEntity> fixedOverheadEntity = fundsService.overHead();

        return "";//没有静态页面
    }

    //项目分红
    @RequestMapping("/dividend")
    public String dividend() {

        return "";//没有静态页面
    }

}
