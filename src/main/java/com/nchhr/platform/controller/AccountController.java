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

    @PostMapping("/login")
    @ResponseBody
    public String login(String phone, String pwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(phone+"::"+pwd);
       return accountService.login(phone,pwd,session,request,response);

    }

    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    @RequestMapping("/recover")
    public String recover() {

        return "recover";
    }
}
