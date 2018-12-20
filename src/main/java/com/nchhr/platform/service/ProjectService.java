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
        return projectInvestDao.getUserStatusInProject(userId, projectId) == 0;
    }
}
