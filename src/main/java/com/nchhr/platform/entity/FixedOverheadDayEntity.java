package com.nchhr.platform.entity;

public class FixedOverheadDayEntity {
    private String FO_day_id;
    private String user_id;
    private String project_id;
    private String time;
    private int amount_day;
    private int expenditureType;

    public String getFO_day_id() {
        return FO_day_id;
    }

    public void setFO_day_id(String FO_day_id) {
        this.FO_day_id = FO_day_id;
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

    public int getAmount_day() {
        return amount_day;
    }

    public void setAmount_day(int amount_day) {
        this.amount_day = amount_day;
    }

    public int getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(int expenditureType) {
        this.expenditureType = expenditureType;
    }

    @Override
    public String toString() {
        return "FixedOverheadDayEntity{" +
                "FO_day_id='" + FO_day_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", time='" + time + '\'' +
                ", amount_day=" + amount_day +
                ", expenditureType=" + expenditureType +
                '}';
    }
}
