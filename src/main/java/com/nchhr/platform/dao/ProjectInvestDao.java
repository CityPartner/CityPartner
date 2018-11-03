package com.nchhr.platform.dao;

import com.nchhr.platform.entity.ProjectInvestEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectInvestDao {


    @Select("select * from project_invest where and project = #{0}")
    List<ProjectInvestEntity> loadList(@Param("0") String project_id);
}
