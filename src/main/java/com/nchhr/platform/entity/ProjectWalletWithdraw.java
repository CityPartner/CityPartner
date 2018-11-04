package com.nchhr.platform.entity;

import java.sql.Timestamp;

public class ProjectWalletWithdraw {
    private String withdrawId;
    private String userId;
    private String projectId;
    private String withdrawAmount;
    private Timestamp applyTime;
    private String applyName;
    private Timestamp handleTime;
    private String handleName;
    private Integer withdrawStatus;

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

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public Timestamp getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Timestamp handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    @Override
    public String toString() {
        return "ProjectWalletWithdraw{" +
                "withdrawId='" + withdrawId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", withdrawAmount=" + withdrawAmount +
                ", applyTime=" + applyTime +
                ", applyName='" + applyName + '\'' +
                ", handleTime=" + handleTime +
                ", handleName='" + handleName + '\'' +
                ", withdrawStatus=" + withdrawStatus +
                '}';
    }
}
