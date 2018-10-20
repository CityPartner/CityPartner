package com.nchhr.platform.dao;

import com.nchhr.platform.entity.ProjectWalletWithdraw;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;


public interface WalletDao {

    /**
     * 查询用户项目的钱包数额
     * @param userId 用户id
     * @param projectId 项目id
     * @return 钱包数额
     */
    @Select("select wallet_amount from project_wallet " +
            "where user_id = #{userId} and project_id = #{projectId}")
    int getWalletAmount(@Param("userId") String userId
            , @Param("projectId") String projectId);

    /**
     * 添加一条提现申请
     * @param withdrawId 提现id
     * @param userId 用户id
     * @param projectId 项目id
     * @param withdrawAmount 提现金额
     * @param applyTime 申请时间
     * @param applyName 申请姓名
     * @param withdrawStatus 提现状态，0
     */
    @Insert("insert into project_wallet_withdraw (withdraw_id" +
            ", user_id" +
            ", project_id" +
            ", withdraw_amount" +
            ", apply_time" +
            ", apply_name" +
            ", withdraw_status) " +
            "values (#{withdrawId}" +
            ", #{userId}" +
            ", #{projectId}" +
            ", #{withdrawAmount}" +
            ", #{applyTime}" +
            ", #{applyName}" +
            ", #{withdrawStatus})")
    void addWalletApplyRecord(@Param("withdrawId") String withdrawId
            , @Param("userId") String userId
            , @Param("projectId") String projectId
            , @Param("withdrawAmount") String withdrawAmount
            , @Param("applyTime") Timestamp applyTime
            , @Param("applyName") String applyName
            , @Param("withdrawStatus") int withdrawStatus);

    /**
     * 查询有没有正在申请的提现（钱包最近一条提现状态）
     * @param userId 用户id
     * @param projectId 项目id
     * @return 最近一条提现申请的状态
     */
    @Select("select withdraw_status from project_wallet_withdraw" +
            " where user_id = #{userId} and project_id = #{projectId}" +
            " order by apply_time desc limit 1")
    Integer getWalletStatus(@Param("userId") String userId
            , @Param("projectId") String projectId);

    /**
     * 查询项目中对应状态的提现记录（申请时间降序）
     * @param projectId 项目id
     * @param withdrawStatus 提现状态
     * @return 项目中对应状态的所有提现记录
     */
    @Select("select * from project_wallet_withdraw" +
            " where project_id = #{projectId}" +
            " and withdraw_status = #{withdrawStatus}" +
            " order by apply_time desc")
    @Results({
            @Result(property = "withdrawId", column = "withdraw_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "withdrawAmount", column = "withdraw_amount"),
            @Result(property = "applyTime", column = "apply_time"),
            @Result(property = "applyName", column = "apply_name"),
            @Result(property = "handleTime", column = "handle_time"),
            @Result(property = "handleName", column = "handle_name"),
            @Result(property = "withdrawStatus", column = "withdraw_status")
    })
    List<ProjectWalletWithdraw> getProjectWithdrawList(@Param("projectId") String projectId
            , @Param("withdrawStatus") Integer withdrawStatus);

    /**
     * 设置提现状态
     * @param withdrawId 提现ID
     * @param withdrawStatus 提现状态
     * @return 1成功，0失败
     */
    @Update("update project_wallet_withdraw set withdraw_status = #{withdrawStatus}" +
            " where withdraw_id = #{withdrawId}")
    Integer setWithdrawStatus(@Param("withdrawId") String withdrawId
            , @Param("withdrawStatus") Integer withdrawStatus);

}