package com.nchhr.platform.dao;

import com.nchhr.platform.ModelVo.indexFundVo;
import com.nchhr.platform.entity.IncomeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IncomeDao {

    /**
     * 从income中获取数据
     * @return
     */
    @Select("SELECT * FROM income ORDER BY time DESC")
    List<IncomeEntity> loadList();

    @Select("select DATE_FORMAT(time, #{0}) times ,SUM(income_amount) income_amounts from income group by times DESC LIMIT #{1},#{2}")
    List<indexFundVo> indexBytime(@Param("0") String s, @Param("1") int fromIndex, @Param("2") int pageSize);

    @Select("SELECT * FROM income ORDER BY time DESC LIMIT #{0},#{1}")
    List<IncomeEntity> indexByDate( @Param("0") int fromIndex, @Param("1") int pageSize);

    //查询income表中的总数
    @Select("SELECT COUNT(*) FROM (select DATE_FORMAT(time,'%Y-%m') times from income  group by times) s")
    int count();

}
