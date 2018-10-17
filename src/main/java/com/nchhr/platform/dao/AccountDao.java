package com.nchhr.platform.dao;

import com.nchhr.platform.entity.PlatformUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountDao {
    @Select("select * from platform_user where phone = #{0}")
    PlatformUserEntity loadByPhone(@Param("0") String phone);

    //添加平台用户
    @Insert("insert into platform_user(P_id,phone,nickname,password,addTime,openid) values(#{0},#{1},'平台用户',#{2},#{3},#{4}) ")
    boolean RegisterLogin(@Param("0") String pid,
                          @Param("1") String userPhone,
                          @Param("2") String pwd,
                          @Param("3") String time,
                          @Param("4") String openid);

    /**
     * 通过id拿用户
     * @param pid
     * @return
     */
    @Select("select * from platform_user where P_id = #{0}")
    PlatformUserEntity loadByid(@Param("0") String pid);

    @Update("update platform_user set password = #{1} where P_id = #{0}")
    boolean updatePwd(@Param("0") String p_id, @Param("1") String newpwd);
}
