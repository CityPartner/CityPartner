package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")

//账号控制器，包括登录、注册、修改密码
public class AccountController {

    @RequestMapping("/login")
    public String login() {

        return "login";
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
