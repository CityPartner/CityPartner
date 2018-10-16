package com.nchhr.platform.entity;

public class WeChatUserEntity {
    private String subscribe;
    private String openid;
    private String nickname;
    private String sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String subscribe_time;
    private String remark;
    private String groupid;
    private String tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
    private String unionid;

    public WeChatUserEntity() {
    }

    //微信普通用户
    public WeChatUserEntity(String openid, String nickname, String sex, String language, String city, String province, String country, String headimgurl) {
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
    }

    //微信公众号关注者
    public WeChatUserEntity(String subscribe, String openid, String nickname, String sex, String language, String city, String province, String country, String headimgurl, String subscribe_time, String remark, String groupid, String tagid_list, String subscribe_scene, String qr_scene, String qr_scene_str) {
        this.subscribe = subscribe;
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
        this.subscribe_time = subscribe_time;
        this.remark = remark;
        this.groupid = groupid;
        this.tagid_list = tagid_list;
        this.subscribe_scene = subscribe_scene;
        this.qr_scene = qr_scene;
        this.qr_scene_str = qr_scene_str;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(String tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getSubscribe_scene() {
        return subscribe_scene;
    }

    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }

    public String getQr_scene() {
        return qr_scene;
    }

    public void setQr_scene(String qr_scene) {
        this.qr_scene = qr_scene;
    }

    public String getQr_scene_str() {
        return qr_scene_str;
    }

    public void setQr_scene_str(String qr_scene_str) {
        this.qr_scene_str = qr_scene_str;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WeChatUserEntity{" +
                " \n subscribe='" + subscribe + '\'' +
                ", \n openid='" + openid + '\'' +
                ", \n nickname='" + nickname + '\'' +
                ", \n sex='" + sex + '\'' +
                ", \n language='" + language + '\'' +
                ", \n city='" + city + '\'' +
                ", \n province='" + province + '\'' +
                ", \n country='" + country + '\'' +
                ", \n headimgurl='" + headimgurl + '\'' +
                ", \n subscribe_time='" + subscribe_time + '\'' +
                ", \n remark='" + remark + '\'' +
                ", \n groupid='" + groupid + '\'' +
                ", \n tagid_list='" + tagid_list + '\'' +
                ", \n subscribe_scene='" + subscribe_scene + '\'' +
                ", \n qr_scene='" + qr_scene + '\'' +
                ", \n qr_scene_str='" + qr_scene_str + '\'' +
                ", \n unionid='" + unionid + '\'' +
                '}';
    }
}
