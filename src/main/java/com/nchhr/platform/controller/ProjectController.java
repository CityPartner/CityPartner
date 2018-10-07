package com.nchhr.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
//平台项目相关控制器层，展示我的项目和项目详情
public class ProjectController {

    //我的项目
    @RequestMapping("/my")
    public String myProject() {

        return "myProject";
    }

    //项目详情
    @RequestMapping("/detail")
    public String projectDetail() {

        return "projectDetail";
    }
}
