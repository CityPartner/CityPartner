package com.nchhr.platform.controller;

import com.nchhr.platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")

//账号控制器，包括登录、注册、修改密码
public class AccountController {

    @Autowired
    AccountService accountService;

    //登录
    @PostMapping("/login")
    @ResponseBody
    public String login(String phone, String pwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(phone + "::" + pwd);
        return accountService.login(phone, pwd, session, request, response);

    }

    //获取验证码
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(String phone, HttpSession session) {
        System.out.println("phone:" + phone);
        return accountService.getCode(phone, session);
    }

    //删除验证码
    @RequestMapping("/deleteCode")
    @ResponseBody
    public String deleteCode(String phone, HttpSession session) {
        return accountService.deleteCode(phone, session);
    }

    //注册并登陆
    @RequestMapping("/RegisterLogin")
    @ResponseBody
    public String register(String userPhone, String code, String pwd, HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        return accountService.RegisterLogin(userPhone, code, pwd, session, response, request);
    }
    //重置密码
    @RequestMapping("/ResetPassword")
    @ResponseBody
    public String ResetPassword(String phone,HttpServletResponse response,HttpSession session) {
//        System.out.println(phone);
        return  accountService.ResetPassword(phone,response,session);

    }

    @RequestMapping("/ChangePassword")
    @ResponseBody
    public String ChangePassword(String newpwd,String code,String newphone,HttpSession session){
        return accountService.ChangePassword(newpwd,code,newphone,session);
    }

}
