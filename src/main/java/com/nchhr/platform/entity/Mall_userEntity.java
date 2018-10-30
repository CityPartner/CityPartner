package com.nchhr.platform.entity;

public class Mall_userEntity {
    private String M_id;
    private String R_id;
    private String SC_id;
    private String phone;
    private String nickname;
    private String ID_card;
    private String name;
    private String openid;
    private String password;
    private String addTime;

    public String getM_id() {
        return M_id;
    }

    public void setM_id(String m_id) {
        M_id = m_id;
    }

    public String getR_id() {
        return R_id;
    }

    public void setR_id(String r_id) {
        R_id = r_id;
    }

    public String getSC_id() {
        return SC_id;
    }

    public void setSC_id(String SC_id) {
        this.SC_id = SC_id;
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

    public String getID_card() {
        return ID_card;
    }

    public void setID_card(String ID_card) {
        this.ID_card = ID_card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Mall_userEntity{" +
                "M_id='" + M_id + '\'' +
                ", R_id='" + R_id + '\'' +
                ", SC_id='" + SC_id + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", ID_card='" + ID_card + '\'' +
                ", name='" + name + '\'' +
                ", openid='" + openid + '\'' +
                ", password='" + password + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
