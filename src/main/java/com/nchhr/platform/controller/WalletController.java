package com.nchhr.platform.controller;

import com.nchhr.platform.entity.ProjectWalletWithdraw;
import com.nchhr.platform.service.WalletService;
import com.nchhr.platform.util.GetCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/wallet")

//项目钱包控制器，包括我的收入详情、收入提现
public class WalletController {

    @Autowired
    WalletService walletService;

    /*
        我的钱包
        @Author HWG
            1.从project_wallet读取数据，显示钱包数额
            2.提供“收入”按钮，链接“收入详情页面”
            3.提供“提现”按钮，链接“提现功能页面”
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
    public String withdeawDetail() {

        return "withdrawDetail";
    }




    /*
        钱包——盈利提现
        @Author JC
     */
    @RequestMapping("/withdraw")
    public String withdraw(HttpSession httpSession, Model model) {
        //TODO 以下为测试数据
        httpSession.setAttribute("userId", "MVsmdpmqG0p7FqSi7");//测试用户
        httpSession.setAttribute("projectId", "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz");//测试项目
        httpSession.setAttribute("phone", "18160742626");//测试用户手机号

        String userId = (String) httpSession.getAttribute("userId");
        String projectId = (String) httpSession.getAttribute("projectId");
        model.addAttribute("walletAmount", walletService.getWalletAmount(userId, projectId));
        model.addAttribute("applyName", walletService.getApplyName(userId, projectId));
        return "withdraw";
    }
    //获取验证码
    @RequestMapping("/withdraw/getCode")
    @ResponseBody
    public String getCode(HttpSession httpSession) {
        System.out.println("--短信已发送--");
        String phone = (String) httpSession.getAttribute("phone");
        return GetCodeUtils.getCode(phone, httpSession, "0");
    }
    //判断当前是否有申请未处理
    @RequestMapping("/withdraw/isApplying")
    @ResponseBody
    public String isApplying(HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");
        String projectId = (String) httpSession.getAttribute("projectId");
        return walletService.isApplying(userId, projectId) ? "1" : "0";
    }
    //添加申请
    @RequestMapping("/withdraw/apply")
    @ResponseBody
    public String applyWithdraw(HttpSession httpSession, String name, String money, String code) {
        String userId = (String) httpSession.getAttribute("userId");
        String projectId = (String) httpSession.getAttribute("projectId");
        String phone = (String) httpSession.getAttribute("phone");
        return walletService.addWalletApplyRecord(userId, projectId, name, money, phone, code, httpSession);
    }


    /*
        处理提现
        @author JC
     */
    @RequestMapping("/withdraw/msg")
    public String withdrawMsg(HttpSession httpSession, Model model) {
        String projectId = (String) httpSession.getAttribute("projectId");
        model.addAttribute("withdrawMsgs", walletService.getWithdrawApplyList(projectId));
        return "withdrawMsg";
    }

    @RequestMapping("/withdraw/handle")
    @ResponseBody
    public String dealwith(String withdrawId) {
        return walletService.handleWithdraw(withdrawId).toString();
    }
}
