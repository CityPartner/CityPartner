package com.nchhr.platform.dao;

import com.nchhr.platform.entity.PlatformUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountDao {
    @Select("select * from platform_user where phone = #{0}")
    PlatformUser loadByPhone(@Param("0") String phone);

}
