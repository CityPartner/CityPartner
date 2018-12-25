package com.nchhr.platform.dao;

import com.nchhr.platform.entity.ProjectInvestEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectInvestDao {


    @Select("select * from project_invest where and project = #{0}")
    List<ProjectInvestEntity> loadList(@Param("0") String project_id);

    @Select("select user_status from project_invest where user_id = #{userId} and project_id = #{projectId}")
    Integer getUserStatusInProject(@Param("userId") String userId, @Param("projectId") String projectId);
}
