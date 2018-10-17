package com.nchhr.platform.util;

import com.nchhr.platform.entity.PhoneCodeEntity;

import javax.servlet.http.HttpSession;

/**
 * 将手机号和code绑定在一起
 */
public class PhoneCodeUtils {
    /**
     * 将手机号和code 绑定在一起放在session中
     * @param phone
     * @param code
     * @param session
     * @return
     */
    public boolean save(String phone, String code, HttpSession session){

        if (session.getAttribute(phone)!= null){
            return false;
        }
        try{
            PhoneCodeEntity phoneCode = new PhoneCodeEntity();
            MD5Utils md5Utils = new MD5Utils();
            phoneCode.setPhone(phone);
            phoneCode.setCode(md5Utils.MD5Encode(code,"utf8"));

            session.setAttribute(phone,phoneCode);
        }catch(Exception e){

        return false;
        }
       return true;
    }
}
