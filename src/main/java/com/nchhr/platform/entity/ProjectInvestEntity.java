package com.nchhr.platform.entity;

import java.io.Serializable;

public class ProjectInvestEntity implements Serializable{

    private String invest_id;
    private String user_id;
    private String project_id;
    private int user_status;
    private String user_name;
    private int invest_amount;
    private String invest_time;
    private int invest_stock;

    public String getInvest_id() {
        return invest_id;
    }

    public void setInvest_id(String invest_id) {
        this.invest_id = invest_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getInvest_amount() {
        return invest_amount;
    }

    public void setInvest_amount(int invest_amount) {
        this.invest_amount = invest_amount;
    }

    public String getInvest_time() {
        return invest_time;
    }

    public void setInvest_time(String invest_time) {
        this.invest_time = invest_time;
    }

    public int getInvest_stock() {
        return invest_stock;
    }

    public void setInvest_stock(int invest_stock) {
        this.invest_stock = invest_stock;
    }

    @Override
    public String toString() {
        return "ProjectInvestEntity{" +
                "invest_id='" + invest_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", user_status=" + user_status +
                ", user_name='" + user_name + '\'' +
                ", invest_amount=" + invest_amount +
                ", invest_time='" + invest_time + '\'' +
                ", invest_stock=" + invest_stock +
                '}';
    }
}
