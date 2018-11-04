package com.nchhr.platform.dao;

import com.nchhr.platform.entity.FixedOverheadEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FixedOverheadDao {

    /**
     * 从fixed_overhead中获取数据
     * @return
     */
    @Select("select * from fixed_overhead")
    List<FixedOverheadEntity> loadList();

    /**
     * 向固定开销表中插入分红记录
     * @param FO_id
     * @param user_id
     * @param project_id
     * @param time
     * @param amount
     * @param expenditureType
     * @return
     */
    @Insert("insert into fixed_overhead values(#{0},#{1},#{2},#{3},#{4},#{5})")
    boolean InsertDividend(@Param("0") String FO_id,@Param("1") String user_id,@Param("2") String project_id,@Param("3") String time,@Param("4") int amount,@Param("5") int expenditureType);

    /**
     * 按时间顺序排序，查出所有分红支出
     * @param expenditureType
     * @return
     */
    @Select("select * from fixed_overhead where expenditureType = #{0} ORDER BY time DESC")
    List<FixedOverheadEntity> loadDivList(@Param("0") int expenditureType);
}
