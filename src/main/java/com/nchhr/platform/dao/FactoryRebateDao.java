package com.nchhr.platform.dao;

import com.nchhr.platform.entity.FactoryRebateEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FactoryRebateDao {

    /**
     * 从fixed_overhead中获取数据
     * @return
     */
    @Select("select * from factory_rebate")
    List<FactoryRebateEntity> loadList();
}
