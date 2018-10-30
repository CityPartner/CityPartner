package com.nchhr.platform.dao;

import com.nchhr.platform.entity.Mall_userEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Mall_userDao {

    @Select("select * from mall_user")
    List<Mall_userEntity> loadList();

    @Select("select * from mall_user where m_id = #{0}")
    Mall_userEntity loadById(@Param("0") String m_id);
}
