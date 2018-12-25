package com.nchhr.platform.dao;

import com.nchhr.platform.entity.IncomeDayEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IncomeDayDao {

    @Select("select * from income_day")
    List<IncomeDayEntity> loadList();
}
