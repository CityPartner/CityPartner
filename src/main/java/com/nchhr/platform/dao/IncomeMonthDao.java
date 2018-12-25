package com.nchhr.platform.dao;

import com.nchhr.platform.entity.IncomeMonthEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IncomeMonthDao {

    @Select("select * from income_month")
    List<IncomeMonthEntity> loadList();
}
