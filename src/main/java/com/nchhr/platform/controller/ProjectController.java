package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")

//平台项目控制器层，包括展示我的项目和项目详情
public class ProjectController {

    //我的项目
    @RequestMapping("/my")
    public String myProject() {

        return "myProject";
    }

    //项目详情（控制用户使用的功能）
    @RequestMapping("/detail")
    public String projectDetail() {

        return "projectDetail";
    }
}
