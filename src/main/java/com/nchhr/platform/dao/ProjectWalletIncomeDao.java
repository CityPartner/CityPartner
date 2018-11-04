package com.nchhr.platform.dao;

import com.nchhr.platform.entity.ProjectWalletIncome;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectWalletIncomeDao {

    @Insert("insert into project_wallet_income values(#{0})")
    boolean insertDivIncome(@Param("0") String income_id, @Param("1") String user_id, @Param("2") String project_id,
                         @Param("3") double income_amount, @Param("4") String time, @Param("4") char c, @Param("5") String fo_id);

    @Select("select * from project_wallet_income where attach_info = #{0}")
    List<ProjectWalletIncome> loadIncomeList(@Param("0") String FO_id);
}
