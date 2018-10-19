package com.nchhr.platform.entity;

public class ProjectWalletIncome {
    private String incomeId;
    private String userId;
    private String projectId;
    private String incomeAmount;
    private String incomeTime;
    private String incomeType;
    private String attachInfo;

    public ProjectWalletIncome() {
    }

    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(String incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(String incomeTime) {
        this.incomeTime = incomeTime;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getAttachInfo() {
        return attachInfo;
    }

    public void setAttachInfo(String attachInfo) {
        this.attachInfo = attachInfo;
    }

    @Override
    public String toString() {
        return "ProjectWalletIncome{" +
                "incomeId='" + incomeId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", incomeAmount='" + incomeAmount + '\'' +
                ", incomeTime='" + incomeTime + '\'' +
                ", incomeType='" + incomeType + '\'' +
                ", attachInfo='" + attachInfo + '\'' +
                '}';
    }
}
