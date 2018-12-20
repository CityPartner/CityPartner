package com.nchhr.platform.entity;

public class FixedOverheadYearEntity {
    private String FO_year_id;
    private String user_id;
    private String project_id;
    private String time;
    private int amount_year;
    private int expenditureType;

    public String getFO_year_id() {
        return FO_year_id;
    }

    public void setFO_year_id(String FO_year_id) {
        this.FO_year_id = FO_year_id;
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

    public int getAmount_year() {
        return amount_year;
    }

    public void setAmount_year(int amount_year) {
        this.amount_year = amount_year;
    }

    public int getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(int expenditureType) {
        this.expenditureType = expenditureType;
    }

    @Override
    public String toString() {
        return "FixedOverheadYearEntity{" +
                "FO_year_id='" + FO_year_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", time='" + time + '\'' +
                ", amount_year=" + amount_year +
                ", expenditureType=" + expenditureType +
                '}';
    }
}
