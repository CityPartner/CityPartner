package com.nchhr.platform.dao;

import com.nchhr.platform.ModelVo.WalletProVo;
import com.nchhr.platform.entity.ProjectWalletIncome;
import com.nchhr.platform.entity.ProjectWalletWithdraw;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;


public interface WalletDao {

    /**
     * 查询用户项目的钱包数额
     * @author JC
     * @param userId 用户id
     * @param projectId 项目id
     * @return 钱包数额
     */
    @Select("select wallet_amount from project_wallet " +
            "where user_id = #{userId} and project_id = #{projectId}")
    String getWalletAmount(@Param("userId") String userId
            , @Param("projectId") String projectId);

    /**
     * 添加一条提现申请
     * @author JC
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
     * @author JC
     * @param userId 用户id
     * @param projectId 项目id
     * @return 最近一条提现申请的状态
     */
    @Select("select withdraw_status from project_wallet_withdraw" +
            " where user_id = #{userId} and project_id = #{projectId}" +
            " order by apply_time desc limit 1")
    Integer getLatestWalletStatus(@Param("userId") String userId
            , @Param("projectId") String projectId);

    /**
     * 根据提现id查询提现状态
     * @author JC
     * @param withdrawId 提现id
     * @return 提现状态
     */
    @Select("select withdraw_status from project_wallet_withdraw" +
            " where withdraw_id = #{withdrawId}")
    Integer getWithdrawStatus(@Param("withdrawId") String withdrawId);

    /**
     * 查询项目中对应状态的提现记录（申请时间降序）
     * @author JC
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
     * 更新提现处理
     * @author JC
     * @param withdrawId 提现ID
     * @param withdrawStatus 提现状态
     * @return 1成功，0失败
     */
    @Update("update project_wallet_withdraw set handle_time = #{handleTime}" +
            ", handle_name = #{handleName}" +
            ", withdraw_status = #{withdrawStatus}" +
            " where withdraw_id = #{withdrawId}")
    Integer setWithdrawStatus(@Param("withdrawId") String withdrawId
            ,@Param("handleTime") Timestamp handleTime
            , @Param("handleName") String handleName
            , @Param("withdrawStatus") Integer withdrawStatus);

    /**
     * 更新用户项目钱包数额
     * @author JC
     * @param userId 用户id
     * @param projectId 项目id
     * @param walletAmount 钱包数额
     * @return 更新钱包状态
     */
    @Update("update project_wallet set wallet_amount = #{walletAmount} " +
            "where user_id = #{userId} and project_id = #{projectId}")
    boolean updateWalletAmount(@Param("userId") String userId
            , @Param("projectId") String projectId
            , @Param("walletAmount") String walletAmount);

    /**
     * 通过提现id获取该条提现信息
     * @author JC
     * @param withdrawId 提现id
     * @return 提现信息
     */
    @Select("select * from project_wallet_withdraw where withdraw_id = #{withdrawId}")
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
    ProjectWalletWithdraw getWithdrawById(@Param("withdrawId") String withdrawId);




    /**
     * 查询用户钱包
     * @param user_id 用户id
     */
    @Select("SELECT w.wallet_id,w.user_id,w.project_id,w.wallet_amount,p.name from " +
            "(select * from project_wallet where user_id=#{user_id}) AS w JOIN " +
            "project as p ON w.project_id=p.project_id")
    List<WalletProVo> getWallet(@Param("user_id") String user_id);


    /**
     * 查询用户项目的钱包
     * @param user_id 用户id
     */
    @Select("SELECT w.wallet_id,w.user_id,w.project_id,w.wallet_amount,p.name from " +
            "(select * from project_wallet where user_id=#{user_id} and project_id=#{project_id}) AS w JOIN " +
            "project as p ON w.project_id=p.project_id")
    List<WalletProVo> getProWallet(@Param("user_id") String user_id,@Param("project_id")String project_id);

    /**
     * 查询用户的所有提现记录（申请时间降序）
     * @param userId 用户平台id
     * @return 用户的所有提现记录
     */
    @Select("SELECT * from " +
            "(SELECT * from project_wallet_withdraw where user_id=#{userId}) AS w" +
            " join project as p " +
            "on w.project_id= p.project_id ;")
    @Results({
            @Result(property = "withdrawId", column = "withdraw_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "name"),
            @Result(property = "withdrawAmount", column = "withdraw_amount"),
            @Result(property = "applyTime", column = "apply_time"),
            @Result(property = "applyName", column = "apply_name"),
            @Result(property = "handleTime", column = "handle_time"),
            @Result(property = "handleName", column = "handle_name"),
            @Result(property = "withdrawStatus", column = "withdraw_status")
    })
    List<ProjectWalletWithdraw> getAllWithdrawList(@Param("userId") String userId);

    /**
     * 查询用户项目提现记录
     * @param userId
     * @param project_id
     * @return
     */
    @Select("SELECT * from " +
            "(SELECT * from project_wallet_withdraw where user_id=#{userId} and project_id=#{project_id}) AS w" +
            " join project as p " +
            "on w.project_id= p.project_id  order by apply_time desc  ;")
    @Results({
            @Result(property = "withdrawId", column = "withdraw_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "name"),
            @Result(property = "withdrawAmount", column = "withdraw_amount"),
            @Result(property = "applyTime", column = "apply_time"),
            @Result(property = "applyName", column = "apply_name"),
            @Result(property = "handleTime", column = "handle_time"),
            @Result(property = "handleName", column = "handle_name"),
            @Result(property = "withdrawStatus", column = "withdraw_status")
    })
    List<ProjectWalletWithdraw> getProWithdrawList(@Param("userId") String userId,@Param("project_id")String project_id);

    /**
     * 获取所有用户收入信息
     * @param userId 用户id
     * @return
     * HWG
     */
    @Select("select * from " +
            "(select * from project_wallet_income where user_id=#{userId}) as i " +
            "JOIN project as p on i.project_id=p.project_id;")
    @Results({
            @Result(property = "incomeId",column = "income_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "projectId",column = "name"),
            @Result(property = "incomeAmount",column = "income_amount"),
            @Result(property = "incomeTime",column = "income_time"),
            @Result(property = "incomeType",column = "income_type"),
            @Result(property = "attachInfo",column = "attach_info")
    })
    List<ProjectWalletIncome> getAllIncomeList(@Param("userId")String userId);

    /**
     * 获取所有用户某项目的收入信息
     * @param userId
     * @param project_id
     * @return
     * HWG
     */
    @Select("select * from " +
            "(select * from project_wallet_income where user_id=#{userId} and project_id=#{project_id}) as i " +
            "JOIN project as p on i.project_id=p.project_id  order by income_time desc;")
    @Results({
            @Result(property = "incomeId",column = "income_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "projectId",column = "name"),
            @Result(property = "incomeAmount",column = "income_amount"),
            @Result(property = "incomeTime",column = "income_time"),
            @Result(property = "incomeType",column = "income_type"),
            @Result(property = "attachInfo",column = "attach_info")
    })
    List<ProjectWalletIncome> getProIncomeList(@Param("userId")String userId,@Param("project_id")String project_id);
}
