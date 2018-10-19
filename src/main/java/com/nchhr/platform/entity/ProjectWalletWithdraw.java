package com.nchhr.platform.entity;

import java.sql.Timestamp;

public class ProjectWalletWithdraw {
    private String withdrawId;
    private String userId;
    private String projectId;
    private Integer withdrawAmount;
    private Timestamp applyTime;
    private Timestamp handleTime;
    private String handleUser;
    private Integer withdrawStaus;

    public ProjectWalletWithdraw() {
    }

    public String getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(String withdrawId) {
        this.withdrawId = withdrawId;
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

    public Integer getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(Integer withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public Timestamp getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Timestamp handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleUser() {
        return handleUser;
    }

    public void setHandleUser(String handleUser) {
        this.handleUser = handleUser;
    }

    public Integer getWithdrawStaus() {
        return withdrawStaus;
    }

    public void setWithdrawStaus(Integer withdrawStaus) {
        this.withdrawStaus = withdrawStaus;
    }

    @Override
    public String toString() {
        return "ProjectWalletWithdraw{" +
                "withdrawId='" + withdrawId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", withdrawAmount='" + withdrawAmount + '\'' +
                ", applyTime=" + applyTime +
                ", handleTime=" + handleTime +
                ", handleUser='" + handleUser + '\'' +
                ", withdrawStaus='" + withdrawStaus + '\'' +
                '}';
    }
}
