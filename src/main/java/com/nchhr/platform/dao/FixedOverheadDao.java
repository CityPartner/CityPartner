package com.nchhr.platform.dao;

import com.nchhr.platform.entity.FixedOverheadEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FixedOverheadDao {

    /**
     * 从fixed_overhead中获取数据
     * @return
     */
    @Select("select * from fixed_overhead")
    List<FixedOverheadEntity> loadList();
}
