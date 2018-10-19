package com.nchhr.platform.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;


public interface WalletDao {

    //查询某个用户某个项目的钱包额度
    @Select("select wallet_amount from project_wallet " +
            "where user_id = #{userId} and project_id = #{projectId}")
    int getWalletAmount(@Param("userId") String userId
            , @Param("projectId") String projectId);

    //添加钱包提现申请记录
    @Insert("insert into project_wallet_withdraw (withdraw_id" +
            ", user_id" +
            ", project_id" +
            ", withdraw_amount" +
            ", apply_time" +
            ", withdraw_status) " +
            "values (#{withdrawId}" +
            ", #{userId}" +
            ", #{projectId}" +
            ", #{withdrawAmount}" +
            ", #{applyTime}" +
            ", #{withdrawStatus})")
    void addWalletApplyRecord(@Param("withdrawId") String withdrawId
            , @Param("userId") String userId
            , @Param("projectId") String projectId
            , @Param("withdrawAmount") String withdrawAmount
            , @Param("applyTime") Timestamp applyTime
            , @Param("withdrawStatus") int withdrawStatus);

    //查询有没有正在申请的提现
    @Select("select withdraw_status from project_wallet_withdraw " +
            "where user_id = #{userId} and project_id = #{projectId} " +
            "order by apply_time desc limit 1")
    Integer getWalletStatus(@Param("userId") String userId
            , @Param("projectId") String projectId);



}
