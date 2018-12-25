package com.nchhr.platform.dao;

import com.nchhr.platform.ModelVo.indexFundVo;
import com.nchhr.platform.entity.FactoryRebateEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FactoryRebateDao {

    /**
     * 从fixed_overhead中获取数据
     * @return
     * @param fromIndex
     * @param pageSize
     */
    @Select("select * from factory_rebate ORDER BY rebate_time DESC  LIMIT #{0},#{1}")
    List<FactoryRebateEntity> loadList(@Param("0") int fromIndex , @Param("1") int pageSize);


    @Select("select count(*) from factory_rebate")
    int count();

    //获取年
    @Select("select DATE_FORMAT(rebate_time, #{0}) times ,SUM(rebate_amount) income_amounts from factory_rebate group by times DESC LIMIT #{1},#{2}")
    List<indexFundVo> indexBytime(@Param("0") String s,@Param("1") int fromIndex, @Param("2") int pageSize);
}
