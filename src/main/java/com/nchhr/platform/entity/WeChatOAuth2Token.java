package com.nchhr.platform.entity;

/*
    通过两种scope： snsapi_base 和 snsapi_userinfo
    存取access_token 和 openid
 */
public class WeChatOAuth2Token {
    private String access_token;
    private String refresh_token;
    private String openid;
    private String scope;
    private String expires_in;

    public WeChatOAuth2Token() {
    }

    public WeChatOAuth2Token(String access_token, String refresh_token, String openid, String scope, String expires_in) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.openid = openid;
        this.scope = scope;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "WeChatOAuth2Token{" +
                " \n access_token='" + access_token + '\'' +
                ", \n refresh_token='" + refresh_token + '\'' +
                ", \n openid='" + openid + '\'' +
                ", \n scope='" + scope + '\'' +
                ", \n expires_in='" + expires_in + '\'' +
                '}';
    }
}
