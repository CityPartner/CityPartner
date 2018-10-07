package com.nchhr.platform.util;

import com.alibaba.fastjson.JSONObject;

public class WeChatUtil {

    public static final String AppID = "wxd37234f803fb499e";

    public static final String AppSecret = "197e3656caf846e48d7ce9a3cf9ddba9";

    public static String access_token = "";

    //中控服务器统一获取刷新access_token
    public static void getAccess_token() {
        System.out.println("中控服务器获取刷新access_token开始");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        String SubscriberTokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                                + "&appid=" + WeChatUtil.AppID
                                + "&secret=" + WeChatUtil.AppSecret;
                        JSONObject SubscriberTokenJSON = JSONObject.parseObject(JSONUtil.getJSONByURL(SubscriberTokenURL));
                        WeChatUtil.access_token = SubscriberTokenJSON.getString("access_token");
                        System.out.println("循环测试：" + WeChatUtil.access_token);
                        Thread.sleep((7200-180)*1000);
                    } while (true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
