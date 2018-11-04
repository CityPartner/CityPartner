package com.nchhr.platform.dao;

import com.nchhr.platform.entity.PlatformUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PlatformUserDao {

    @Select("select * from platform_user where P_id = #{0}")
    PlatformUserEntity loadList(@Param("0") String userId);

}
