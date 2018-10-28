package com.nchhr.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.nchhr.platform.dao.WeChatUserDao;
import com.nchhr.platform.entity.WeChatOAuth2Token;
import com.nchhr.platform.entity.WeChatUserEntity;
import com.nchhr.platform.util.JSONUtil;
import com.nchhr.platform.util.WeChatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class WeChatUserService {


    @Resource
    private WeChatUserDao weChatUserDao;

    private WeChatOAuth2Token weChatOAuth2Token;

    private WeChatUserEntity weChatUser;

    //获取微信验证请求路径
    public String getWeChatRequestURL() {
        //回调页面路径
        String wechat_redirect_uri = "http://haoduodian.trunch.cn/shop/subscriber/wechat_redirect";
        //授权请求路径
        String request_url = null;
        try {
            request_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeChatUtil.AppID
                    + "&redirect_uri="+ URLEncoder.encode(wechat_redirect_uri, "UTF-8")
                    + "&response_type=code"
                    + "&scope=snsapi_base" //静默授权
                    + "&state=STATE#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return request_url;
    }

    //静默获取token和openid（该方法主要获取openid）
    public void getWeChatOAuth2Token(String code, String state) {
        String WeChatOAuth2TokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeChatUtil.AppID
                + "&secret=" + WeChatUtil.AppSecret
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject WeChatOAuth2TokenJSON = JSONObject.parseObject(JSONUtil.getJSONByURL(WeChatOAuth2TokenURL));
        //实体类保存信息
        weChatOAuth2Token = new WeChatOAuth2Token();
        weChatOAuth2Token.setAccess_token(WeChatOAuth2TokenJSON.getString("access_token"));
        weChatOAuth2Token.setRefresh_token(WeChatOAuth2TokenJSON.getString("refresh_token"));
        weChatOAuth2Token.setOpenid(WeChatOAuth2TokenJSON.getString("openid"));
        weChatOAuth2Token.setScope(WeChatOAuth2TokenJSON.getString("scope"));
        weChatOAuth2Token.setExpires_in(WeChatOAuth2TokenJSON.getString("expires_in"));
        System.out.println("TEST SERVICE token:" + weChatOAuth2Token.toString());
    }

    //获取用户微信openId
    public String getOpenid() {
        return weChatOAuth2Token.getOpenid();
    }

    //获得用户微信信息
    public WeChatUserEntity getUser() {
        //配置token、openid和lang
        String access_token = WeChatUtil.access_token;
        String openid = weChatOAuth2Token.getOpenid();
        String lang = "zh_CN";
        //配置路径
        String WeChatUserInfoURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token
                + "&openid=" + openid
                + "&lang=" + lang;
        //解析保存JSON数据
        JSONObject WeChatUserJSON = JSONObject.parseObject(JSONUtil.getJSONByURL(WeChatUserInfoURL));
        weChatUser = new WeChatUserEntity();
        weChatUser.setSubscribe(WeChatUserJSON.getString("subscribe"));
        weChatUser.setOpenid(WeChatUserJSON.getString("openid"));
        weChatUser.setNickname(WeChatUserJSON.getString("nickname"));
        weChatUser.setSex(WeChatUserJSON.getString("sex"));
        weChatUser.setLanguage(WeChatUserJSON.getString("language"));
        weChatUser.setCity(WeChatUserJSON.getString("city"));
        weChatUser.setProvince(WeChatUserJSON.getString("province"));
        weChatUser.setCountry(WeChatUserJSON.getString("country"));
        weChatUser.setHeadimgurl(WeChatUserJSON.getString("headimgurl"));
        weChatUser.setSubscribe_time(WeChatUserJSON.getString("subscribe_time"));
        weChatUser.setRemark(WeChatUserJSON.getString("remark"));
        weChatUser.setGroupid(WeChatUserJSON.getString("groupid"));
        weChatUser.setTagid_list(WeChatUserJSON.getString("tagid_list"));
        weChatUser.setSubscribe_scene(WeChatUserJSON.getString("subscribe_scene"));
        weChatUser.setQr_scene(WeChatUserJSON.getString("qr_scene"));
        weChatUser.setQr_scene_str(WeChatUserJSON.getString("qr_scene_str"));
        System.out.println(weChatUser.toString());
        return weChatUser;
    }
}
