package com.nchhr.platform.dao;

import com.nchhr.platform.entity.IncomeYearEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IncomeYearDao {

    @Select("select * from income_year")
    List<IncomeYearEntity> loadList();
}
