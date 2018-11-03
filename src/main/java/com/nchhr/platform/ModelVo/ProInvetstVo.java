package com.nchhr.platform.ModelVo;

import com.nchhr.platform.entity.PlatformUserEntity;
import com.nchhr.platform.entity.ProjectEntity;
import com.nchhr.platform.entity.ProjectInvestEntity;

public class ProInvetstVo {

    private ProjectInvestEntity projectInvestEntity;
    private PlatformUserEntity platformUserEntity;
    private ProjectEntity projectEntity;

    public ProjectInvestEntity getProjectInvestEntity() {
        return projectInvestEntity;
    }

    public void setProjectInvestEntity(ProjectInvestEntity projectInvestEntity) {
        this.projectInvestEntity = projectInvestEntity;
    }

    public PlatformUserEntity getPlatformUserEntity() {
        return platformUserEntity;
    }

    public void setPlatformUserEntity(PlatformUserEntity platformUserEntity) {
        this.platformUserEntity = platformUserEntity;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    @Override
    public String toString() {
        return "ProInvetstVo{" +
                "projectInvestEntity=" + projectInvestEntity +
                ", platformUserEntity=" + platformUserEntity +
                ", projectEntity=" + projectEntity +
                '}';
    }
}
