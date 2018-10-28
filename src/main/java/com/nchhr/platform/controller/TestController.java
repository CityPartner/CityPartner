package com.nchhr.platform.controller;


import com.nchhr.platform.util.GetCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String userPhone = "13330113797";
        String code = "123456";
//     cookiesService.saveCookies("123",response,request);
        //保存验证码
        GetCodeUtils.save(userPhone,code,session);
//        TemporaryloginEntity temporaryloginEntity = new TemporaryloginEntity();
//        temporaryloginEntity.setCode(code);
//        temporaryloginEntity.setPhone(userPhone);
//        temporaryloginEntity.setPwd("a123456");
//        if (session.getAttribute("temporaryloginEntity") != null){
//            session.removeAttribute("temporaryloginEntity");
//        }
//        session.setAttribute("temporaryloginEntity",temporaryloginEntity);
//     return "redirect:/wechatuser?userPhone="+userPhone+"&code="+code+"&pwd=a123456";
        return  "redirect:"+"/login.html";
    }
}
