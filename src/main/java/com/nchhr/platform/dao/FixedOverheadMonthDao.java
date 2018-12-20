package com.nchhr.platform.dao;

import com.nchhr.platform.entity.FixedOverheadMonthEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FixedOverheadMonthDao {

    @Select("select * from fixed_overhead_month")
    List<FixedOverheadMonthEntity> loadList();
}
