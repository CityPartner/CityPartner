package com.nchhr.platform.service;

import com.nchhr.platform.dao.ProjectInvestDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


//平台项目相关业务层
@Service
public class ProjectService {
    @Resource
    ProjectInvestDao projectInvestDao;
    public boolean isProjectSponsor(String userId, String projectId) {
        Integer projectStatus = projectInvestDao.getUserStatusInProject(userId, projectId);
        if (projectStatus == null)
            return false;
        else
            return projectStatus == 0;
    }
}
