package com.nchhr.platform.util;

import com.alibaba.fastjson.JSONObject;

public class WeChatUtil {

    public static final String AppID = "wxfdcc608f745e9fc8";

    public static final String AppSecret = "552cf5e2338b17cdd0a8606861805767";

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
