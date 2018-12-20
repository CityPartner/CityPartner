package com.nchhr.platform.controller;

import com.nchhr.platform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/project")

//平台项目控制器层，包括展示我的项目和项目详情
public class ProjectController {

    @Autowired
    ProjectService projectService;

    //我的项目
    @RequestMapping ("/my")
    public String myProject(HttpSession session) {

       if (session.getAttribute("PlatformInfo") == null){
           return "redirect:/login.html";
       }

        return "myProject";
    }

    //项目详情（控制用户使用的功能）
    @RequestMapping("/detail")
    public String projectDetail(HttpSession httpSession) {
        httpSession.setAttribute("projectId", "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz");//项目id
        String userId = (String) httpSession.getAttribute("userId");//谁处理
        String projectId = (String) httpSession.getAttribute("projectId");//谁处理
        if (projectService.isProjectSponsor(userId, projectId))
            return "projectDetailSponsor";
        else
            return "projectDetail";
    }
}
