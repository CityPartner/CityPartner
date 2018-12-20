package com.nchhr.platform.dao;

import com.nchhr.platform.entity.IncomeEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IncomeDao {

    /**
     * 从income中获取数据
     * @return
     */
    @Select("SELECT * FROM income ORDER BY time DESC")
    //@Select("select top 10 * from income where In_id not in (select In_id from income order by time DESC) order by time DESC")
    List<IncomeEntity> loadList();


}
