package com.nchhr.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.nchhr.platform.util.SHA1Util;
import com.nchhr.platform.util.WeChatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/jssdk")

//微信接口控制器
public class JSSDKController {
    //JSON测试
    @RequestMapping("")
    @ResponseBody
    public String jsonTest(String url) {
        String appId = WeChatUtil.AppID;
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
        String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        String jsapi_ticket = WeChatUtil.jsapi_ticket;
        String signature = getJSSDKSignature(jsapi_ticket, nonceStr, timestamp, url);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", appId);
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("nonceStr", nonceStr);
        jsonObject.put("signature", signature);

        System.out.println("jsapi_ticket:" + jsapi_ticket
                + "\nnoncestr:" + nonceStr
                + "\ntimestamp:" + timestamp
                + "\nurl:" + url);
        System.out.println("JS-SDK配置签名：" + signature + "\n\n--------------------------------\n");

        return jsonObject.toJSONString();
    }


    //获取JS-SDK配置签名
    private String getJSSDKSignature(String jsapi_ticket, String noncestr, String timestamp, String url) {
        String str = "jsapi_ticket=" + jsapi_ticket
                + "&noncestr=" + noncestr
                + "&timestamp=" + timestamp
                + "&url=" + url;
        return SHA1Util.encryptBySHA1(str);
    }
}
