package com.nchhr.platform.entity;

public class FixedOverheadEntity {
    private String FO_id;
    private String user_id;
    private String project_id;
    private String time;
    private String amount;
    private int expenditureType;

    public String getFO_id() {
        return FO_id;
    }

    public void setFO_id(String FO_id) {
        this.FO_id = FO_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public int getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(int expenditureType) {
        this.expenditureType = expenditureType;
    }

    @Override
    public String toString() {
        return "FixedOverheadEntity{" +
                "FO_id='" + FO_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", time='" + time + '\'' +
                ", amount='" + amount + '\'' +
                ", expenditureType=" + expenditureType +
                '}';
    }
}
