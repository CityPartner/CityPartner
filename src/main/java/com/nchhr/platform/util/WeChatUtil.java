package com.nchhr.platform.util;

import com.alibaba.fastjson.JSONObject;

public class WeChatUtil {

    public static final String AppID = "wxd37234f803fb499e";

    public static final String AppSecret = "197e3656caf846e48d7ce9a3cf9ddba9";

    public static String access_token = "";

    public static String jsapi_ticket = "";

    //中控服务器定时刷新
    public static void CCSR() {
        System.out.println("中控服务器刷新开始，目前刷新参数：access_token、jsapi_ticket");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        getAccess_token();
                        getJsapi_ticket();
                        Thread.sleep((7200-180)*1000);
                    } while (true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //获取JS-SDK配置签名
    public static String getJSSDKSignature(String jsapi_ticket, String noncestr, String timestamp, String url) {
        String str = "jsapi_ticket=" + jsapi_ticket
                + "&noncestr=" + noncestr
                + "&timestamp=" + timestamp
                + "&url=" + url;
        return SHA1Util.encryptBySHA1(str);
    }


    //中控服务器统一获取刷新 access_token
    private static void getAccess_token() {
        System.out.println("刷新access_token:");
        String TokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                + "&appid=" + WeChatUtil.AppID
                + "&secret=" + WeChatUtil.AppSecret;
        JSONObject TokenJSON = JSONObject.parseObject(JSONUtil.getJSONByURL(TokenURL));
        WeChatUtil.access_token = TokenJSON.getString("access_token");
        System.out.println("access_token：" + WeChatUtil.access_token);
    }

    //中控服务器统一获取刷新 jsapi_ticket
    private static void getJsapi_ticket() {
        System.out.println("刷新jsapi_ticket:");
        String TicketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + WeChatUtil.access_token
                + "&type=jsapi";
        JSONObject TokenJSON = JSONObject.parseObject(JSONUtil.getJSONByURL(TicketURL));
        WeChatUtil.jsapi_ticket = TokenJSON.getString("ticket");
        System.out.println("jsapi_ticket：" + WeChatUtil.jsapi_ticket);
    }

    //
}
