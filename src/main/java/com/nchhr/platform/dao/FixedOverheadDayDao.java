package com.nchhr.platform.dao;

import com.nchhr.platform.entity.FixedOverheadDayEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FixedOverheadDayDao {

    @Select("select * from fixed_overhead_day")
    List<FixedOverheadDayEntity> loadList();
}
