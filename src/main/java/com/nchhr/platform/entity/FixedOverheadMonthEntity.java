package com.nchhr.platform.entity;

public class FixedOverheadMonthEntity {
    private String FO_month_id;
    private String user_id;
    private String project_id;
    private String time;
    private int amount_month;
    private int expenditureType;

    public String getFO_month_id() {
        return FO_month_id;
    }

    public void setFO_month_id(String FO_month_id) {
        this.FO_month_id = FO_month_id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmount_month() {
        return amount_month;
    }

    public void setAmount_month(int amount_month) {
        this.amount_month = amount_month;
    }

    public int getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(int expenditureType) {
        this.expenditureType = expenditureType;
    }

    @Override
    public String toString() {
        return "FixedOverheadMonthEntity{" +
                "FO_month_id='" + FO_month_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", time='" + time + '\'' +
                ", amount_month=" + amount_month +
                ", expenditureType=" + expenditureType +
                '}';
    }
}
