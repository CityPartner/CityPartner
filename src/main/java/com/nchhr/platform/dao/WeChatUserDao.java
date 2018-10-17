package com.nchhr.platform.dao;

import com.nchhr.platform.entity.WeChatUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface WeChatUserDao {


    @Insert("insert into  WeChatUser values(" +
            "#{0}," +
            "#{12},"+
            "#{6}," +
            "#{11}," +
            "#{5}," +
            "#{1}," +
            "#{7}," +
            "#{2}," +
            "#{4}," +
            "#{16}," +
            "#{14}," +
            "#{10}," +
            "#{3}," +
            "#{15}," +
            "#{13}," +
            "#{8}," +
            "#{9})")
    boolean addWeCharUser(@Param("0") String openid,
                          @Param("1") String city,
                          @Param("2") String country,
                          @Param("3") String groupid,
                          @Param("4") String headimgurl,
                          @Param("5") String language,
                          @Param("6") String nickname,
                          @Param("7") String province,
                          @Param("8") String qr_scene,
                          @Param("9") String qr_scene_str,
                          @Param("10") String remark,
                          @Param("11")String sex,
                          @Param("12")String subscribe,
                          @Param("13")String subscribe_scene,
                          @Param("14")String subscribe_time,
                          @Param("15")String tagid_list,
                          @Param("16")String unionid);
}
