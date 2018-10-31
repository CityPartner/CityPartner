package com.nchhr.platform.dao;

import com.nchhr.platform.entity.WeChatUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface WeChatUserDao {


    @Insert("insert into  WeChatUser values(" +
            "#{openid}," +
            "#{subscribe},"+
            "#{nickname}," +
            "#{sex}," +
            "#{language}," +
            "#{city}," +
            "#{province}," +
            "#{country}," +
            "#{headimgurl}," +
            "#{subscribe_time}," +
            "#{remark}," +
            "#{groupid}," +
            "#{tagid_list}," +
            "#{subscribe_scene}," +
            "#{qr_scene}," +
            "#{qr_scene_str}," +
            "#{unionid})")
    boolean addWeCharUser(WeChatUserEntity weChatUserEntity);

}
