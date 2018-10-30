package com.nchhr.platform.controller;

import com.nchhr.platform.ModelVo.IncomeVo;
import com.nchhr.platform.entity.FixedOverheadEntity;
import com.nchhr.platform.entity.IncomeEntity;
import com.nchhr.platform.service.FundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/funds")

//项目资金控制器，包括资金流入（项目收入）、资金流出（项目支出）、资金分红
public class FundsController {

    @Autowired
    FundsService fundsService;

    //项目收入
    @RequestMapping("/income")
    @ResponseBody
    public ModelAndView income(Model model) {
        List<IncomeVo> incomeVos = fundsService.income();
        List<FixedOverheadEntity> fixedOverheadEntity = fundsService.overHead();
        model.addAttribute("Incomes",incomeVos);
        model.addAttribute("Expenses",fixedOverheadEntity);
        return new ModelAndView("funds","IncomeModel",model);
    }

    //项目支出
    @RequestMapping("/expenses")
    @ResponseBody
    public  String expenses(Model model) {
        List<FixedOverheadEntity> fixedOverheadEntity = fundsService.overHead();
        model.addAttribute("expenses",fixedOverheadEntity);
        return "";
    }

    //项目分红
    @RequestMapping("/dividend")
    public String dividend() {

        return "";//没有静态页面
    }

}
