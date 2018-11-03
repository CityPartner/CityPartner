package com.nchhr.platform.controller;

import com.nchhr.platform.entity.PlatformUserEntity;
import com.nchhr.platform.service.AccountService;
import com.nchhr.platform.service.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

@Controller
@RequestMapping("")

//账号控制器，包括登录、注册、修改密码
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    CookiesService cookiesService;


    //程序进来入口
    @RequestMapping("")
    public String index(HttpServletRequest request,HttpSession session){
        System.out.println("程序主入口");
        if (cookiesService.alreadyCookies(request,"PID")){
            String pid = cookiesService.printCookies(request,"PID");
            if (!pid.equals("0")){
               PlatformUserEntity platformUserEntity =  accountService.loadByMid(pid);
               //如果不存在该用户，就去登录，重新保存数据
               if (platformUserEntity == null){
                   return "/login.html";
               }
               session.setAttribute("PlatformInfo",platformUserEntity);
                //-------------------!!!
                session.setAttribute("userId", platformUserEntity.getP_id());//用户id
                session.setAttribute("projectId", "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz");//项目id
                session.setAttribute("phone", platformUserEntity.getPhone());//用户手机号
                //-------------------!!!
               return "redirect:/project/my";
            }
        }
        return "/login.html";
    }


    @RequestMapping("/checkCookie")
    public String checkCookie(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        /**
         * 1、先判断有无cookies
         * 2、再判断openid
         */
        if (cookiesService.alreadyCookies(request, "PID")) {
            String pid = cookiesService.printCookies(request, "PID");
            if (pid.equals("0")) {
                //判断openid
                return "/login.html";
            } else {
                PlatformUserEntity platformUserEntity = accountService.loadByMid(pid);
                session.setAttribute("PlatformInfo", platformUserEntity);
                //-------------------!!!
                session.setAttribute("userId", platformUserEntity.getP_id());//用户id
                session.setAttribute("projectId", "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz");//项目id
                session.setAttribute("phone", platformUserEntity.getPhone());//用户手机号
                //-------------------!!!
                return "redirect:/project/my";
            }
        }
        return "/login.html";
    }

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
    public String register(String userPhone, String codess, String pwd, HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        return accountService.RegisterLogin(userPhone, codess, pwd, session, response, request);
    }

    //重置密码
    @RequestMapping("/ResetPassword")
    @ResponseBody
    public String ResetPassword(String phone, HttpServletResponse response, HttpSession session) {
//        System.out.println(phone);
        return accountService.ResetPassword(phone, response, session);

    }

    @RequestMapping("/ChangePassword")
    @ResponseBody
    public String ChangePassword(String newpwd, String code, String newphone, HttpSession session) {
        return accountService.ChangePassword(newpwd, code, newphone, session);
    }
    //注册中间跳转
    @RequestMapping("/register")
    public String register(HttpSession session){
        System.out.println("123");
        if (session.getAttribute("weChatUser") == null) {
            return "redirect:/wechatuser";
        }else {
            return "redirect:/register.html";
        }
    }
    //单纯获取平台用户
    @RequestMapping("/getPlatformInfo")
    public String getPlatformInfo(HttpServletRequest request,HttpSession session){
        String pid = cookiesService.printCookies(request,"PID");
        PlatformUserEntity platformUserEntity = accountService.loadByMid(pid);
        if (platformUserEntity == null){
            return "redirect:/login.html";
        }
        session.setAttribute("getPlatformInfo",platformUserEntity);
        return "redirect:/project/my";
    }

    

}
