package com.nchhr.platform.entity;

public class ProjectWallet {
    private String walletId;
    private String userId;
    private String projectId;
    private String walletAmount;

    public ProjectWallet() {
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
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

    public String getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(String walletAmount) {
        this.walletAmount = walletAmount;
    }

    @Override
    public String toString() {
        return "ProjectWallet{" +
                "walletId='" + walletId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", walletAmount='" + walletAmount + '\'' +
                '}';
    }
}
