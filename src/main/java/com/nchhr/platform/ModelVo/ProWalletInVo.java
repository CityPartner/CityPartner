package com.nchhr.platform.ModelVo;

import com.nchhr.platform.entity.PlatformUserEntity;
import com.nchhr.platform.entity.ProjectWalletIncome;

public class ProWalletInVo {

    private ProjectWalletIncome projectWalletIncome;
    private PlatformUserEntity platformUserEntity;

    public ProjectWalletIncome getProjectWalletIncome() {
        return projectWalletIncome;
    }

    public void setProjectWalletIncome(ProjectWalletIncome projectWalletIncome) {
        this.projectWalletIncome = projectWalletIncome;
    }

    public PlatformUserEntity getPlatformUserEntity() {
        return platformUserEntity;
    }

    public void setPlatformUserEntity(PlatformUserEntity platformUserEntity) {
        this.platformUserEntity = platformUserEntity;
    }

    @Override
    public String toString() {
        return "ProWalletInVo{" +
                "projectWalletIncome=" + projectWalletIncome +
                ", platformUserEntity=" + platformUserEntity +
                '}';
    }
}
