package com.nchhr.platform.entity;

public class PlatformUser {
    private String P_id;
    private String name;
    private String ID_card;
    private String phone;
    private String nickname;
    private String avatar;
    private String sex;
    private String password;

    public String getP_id() {
        return P_id;
    }

    public void setP_id(String p_id) {
        P_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_card() {
        return ID_card;
    }

    public void setID_card(String ID_card) {
        this.ID_card = ID_card;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PlatformUser{" +
                "P_id='" + P_id + '\'' +
                ", name='" + name + '\'' +
                ", ID_card='" + ID_card + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
