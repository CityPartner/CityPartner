package com.nchhr.platform.controller;

import com.nchhr.platform.entity.WeChatOAuth2Token;
import com.nchhr.platform.entity.WeChatUserEntity;
import com.nchhr.platform.service.WeChatUserService;
import com.nchhr.platform.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wechatuser")

//微信用户控制器
public class WeChatUserController {
    @Autowired
    WeChatUserService weChatUserService;

    @RequestMapping("")
    private String weChatAuthorize() {
        //跳转授权验证请求页
        return "redirect:"+weChatUserService.getWeChatRequestURL();
    }

    @RequestMapping("/wechat_redirect")
    public String weChatRedirect(String code, String state, HttpSession session) {
        weChatUserService.getWeChatOAuth2Token(code, state);

        //获取用户微信openid
        String openid = weChatUserService.getOpenid();
        //获取用户微信信息
        WeChatUserEntity weChatUser = weChatUserService.getUser();

        //微信id存在 有账号 return“login”

        //不存在 没有账号 return “register”

        return "";
    }
}
