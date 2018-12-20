package com.nchhr.platform.dao;

import com.nchhr.platform.entity.FixedOverheadYearEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FixedOverheadYearDao {

    @Select("select * from fixed_overhead_year")
    List<FixedOverheadYearEntity> loadList();
}
