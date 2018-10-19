package com.nchhr.platform.controller;

import com.nchhr.platform.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wallet")

//项目钱包控制器，包括我的收入详情、收入提现
public class WalletController {

    @Autowired
    WalletService walletService;

    /*
        钱包
        @Author HWG
            1.从project_wallet读取数据，显示钱包数额
            2.提供“收入”按钮，链接“收入详情”
            3.提供“提现”按钮，链接“提现功能”
                project_wallet数据的来源需要对接
    */
    @RequestMapping("")
    public String wallet() {
        return "wallet";
    }

    /*
        钱包——收入详情
        @Author HWG
            从project_wallet_income读取数据显示
                project_wallet_income数据的来源需要对接
     */
    @RequestMapping("/income/detail")
    public String income() {
        return "incomeDetail";
    }

    /*
    钱包——提现详情
    @Author HWG
        从project_wallet_withdraw读取数据显示
            project_wallet_withdraw数据的来源需要对接
    */
    @RequestMapping("/withdraw/detail")//
    public String withdeawDetail(String withdrawId) {
        // TODO 通过提现id查看提现详细信息
        return "withdrawDetail";
    }



    /*
        钱包——盈利提现
        @Author JC
     */
    @RequestMapping("/withdraw")
    public String withdraw(HttpSession httpSession, Model model) {
        //TODO 以下为测试数据
        httpSession.setAttribute("userId", "18160742626");//测试用户
        httpSession.setAttribute("projectId", "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz");//测试项目

        String userId = (String) httpSession.getAttribute("userId");
        String projectId = (String) httpSession.getAttribute("projectId");
        int walletAmount = walletService.getWalletAmount(userId, projectId);
        model.addAttribute("walletAmount", walletAmount);

        return "withdraw";
    }

    @RequestMapping("/withdraw/getCode")//获取验证码
    @ResponseBody
    public String getCode() {
        //TODO 调用验证短信发送接口
        System.out.println("--短信已发送--");
        return "1";
    }

    @RequestMapping("/withdraw/isApplying")//判断当前是否有申请未处理
    @ResponseBody
    public String isApplying(HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");
        String projectId = (String) httpSession.getAttribute("projectId");
        return walletService.isApplying(userId, projectId) ? "1" : "0";
    }

    @RequestMapping("/withdraw/apply")//添加申请
    @ResponseBody
    public String applyWithdraw(HttpSession httpSession, String name, String money, String code) {
        String userId = (String) httpSession.getAttribute("userId");
        String projectId = (String) httpSession.getAttribute("projectId");
        return walletService.addWalletApplyRecord(userId, projectId, name, money);
    }





    //钱包——项目发起人处理提现
    @RequestMapping("/handle")
    public String dealwith() {

        return "handle";
    }
}
