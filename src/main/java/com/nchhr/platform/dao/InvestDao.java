package com.nchhr.platform.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface InvestDao {

    /**
     *  获取股东姓名，暂时在投资详情表拿取姓名
     * @param userId 用户id
     * @param projectId 项目id
     * @return 用户在这个项目的姓名（发起人前期手动添加投资人时设置）
     */
    @Select("select user_name from project_invest " +
            "where user_id = #{userId} and project_id = #{projectId}")
    String getInvestorNameById(@Param("userId") String userId
            , @Param("projectId") String projectId);

    @Select("select user_id from project_invest " +
            "where project_id = #{projectId} and user_status = 0")
    String getSponsorUserId(@Param("projectId") String projectId);
}
