package com.nchhr.platform.controller;

import com.nchhr.platform.ModelVo.*;
import com.nchhr.platform.entity.FactoryRebateEntity;
import com.nchhr.platform.entity.FixedOverheadEntity;
import com.nchhr.platform.service.FundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
        List<FactoryRebateEntity> factoryRebateEntity = fundsService.Rebate1();



        model.addAttribute("Incomes",incomeVos);
        model.addAttribute("Expenses", factoryRebateEntity);

        return new ModelAndView("funds","IncomeModel",model);
    }

    //项目根据日期查询结果

    /***
     * 0 年
     * 1 月
     * 2 日
     * fromIdex //第几页信息
     * pageSize //加载数据条数
     * @param index
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public indexPage index(String index, String fromIndex, String pageSize, String currentPage){


//        System.out.println("index:"+index);
        indexPage indexPage =  fundsService.index(index,fromIndex,pageSize,currentPage);

        return indexPage;
    }

    //项目支出
    @RequestMapping("/expenses")
    @ResponseBody
    public indexPage expenses(String index, String fromIndex, String pageSize, String currentPage) {

        indexPage indexPage = fundsService.Rebate(index,fromIndex,pageSize,currentPage);

        return indexPage;
    }

    //项目分红
    @RequestMapping("/dividend")
    public String dividend(HttpServletRequest request) {

        //项目分红第一部分：发起人进入项目，发起分红，输入分红金额，插入数据（project_wallet，project_wallet_income，fixed_overhead），前端显示分红记录（分红时间，金额），分红完成

        //从分红html页面中获取，分红的发起人id，分红的项目id，分红的钱amount
        String user_id = "userXiao123";
        String project_id = "pro123456";
        int dividendAmount = 1000;

        boolean proInvetstVoList = fundsService.dividendFund(user_id,project_id,dividendAmount);

        return "";//没有静态页面
    }

    @RequestMapping("dividendList")
    public String dividendList(HttpServletRequest request){

        //项目分红第二部分:显示分红记录（时间，总金额），以及每次分红每个人的详细金额

        //获取分红记录 fixed_overhead
        List<FixedOverheadEntity> fixedOverheadEntities = fundsService.loadDivList();

        return "";
    }

    @RequestMapping("dividendDetail")
    public String dividendDetail(HttpServletRequest request){

        //项目分红第三部分:显示每次分红每个人的详细金额

        //通过request获取到分红id：FO_id
        String FO_id = "";
        List<ProWalletInVo> projectWalletIncomes = fundsService.dividendDetail(FO_id);


        return "";
    }

}
