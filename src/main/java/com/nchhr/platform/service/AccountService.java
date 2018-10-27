package com.nchhr.platform.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.nchhr.platform.entity.WeChatUserEntity;
import com.nchhr.platform.enums.CodeEnum;
import com.nchhr.platform.dao.AccountDao;
import com.nchhr.platform.dao.WeChatUserDao;
import com.nchhr.platform.entity.PhoneCodeEntity;
import com.nchhr.platform.entity.PlatformUserEntity;
import com.nchhr.platform.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AccountService {

    @Resource
    AccountDao accountDao;

    @Resource
    WeChatUserDao weChatUserDao;

    @Autowired
    CookiesService cookiesService;

    public String login(String phone, String pwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(phone);
        if (platformUserEntity == null) {
            //用户不存在
            return "2";
        }
        if (platformUserEntity != null) {
            if (pwd.equals(platformUserEntity.getPassword())) {
                //保存session
                session.setAttribute("PlatformInfo", platformUserEntity);
                //保存cookies
                cookiesService.saveCookies(platformUserEntity.getP_id(),"PID", response, request);
                //登录成功
                return "1";
            } else {
                //密码错误
                return "3";
            }
        } else {
            //系统异常
            return "4";
        }
    }

    public String getCode(String phone, HttpSession session) {
        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(phone);
        if (platformUserEntity == null) {

            return GetCodeUtils.getCode(phone, session,"3");

        }
        if (platformUserEntity != null) {
            //2代表已注册
            return "2";
        }

        return "3";
    }

    //清除验证码
    public String deleteCode(String phone, HttpSession session) {
        PhoneCodeEntity phonecode = (PhoneCodeEntity) session.getAttribute(phone);
        if (phonecode == null) {
            //清除成功
            System.out.println("清除成功");
            return "1";
        } else {
            try {
                session.removeAttribute(phone);
                System.out.println("清除成功");
                return "1";
            } catch (Exception e) {
                //清除失败
                System.out.println("清除失败");
                System.out.println(e.getMessage());
                return "2";

            }

        }
    }

    @Transactional
    public String RegisterLogin(String userPhone, String code, String pwd, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        MD5Utils md5Utils = new MD5Utils();
        //再次判断注册手机号已被注册（是否）
        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(userPhone);
        if (platformUserEntity == null) {
            PhoneCodeEntity phoneCode = (PhoneCodeEntity) session.getAttribute(userPhone);
            if (phoneCode == null) {
                //验证码过期
                return "5";
            }
            if (userPhone.equals(phoneCode.getPhone())) {
                String mdCode;
                try {
                    mdCode = md5Utils.MD5Encode(code, "utf8");

                } catch (Exception e) {
                    return "4";
                }
                if (mdCode.equals(phoneCode.getCode())) {
                    //1成功
                    //添加商城用户
                    CodeUtils codeUtils = new CodeUtils();
                    //获取微信id
                    WeChatUserEntity weChatUserEntity = (WeChatUserEntity)session.getAttribute("weChatUser");
//                    WeChatUserEntity weChatUserEntity = new WeChatUserEntity();

                    weChatUserDao.addWeCharUser(weChatUserEntity.getOpenid(),
                            weChatUserEntity.getCity(),
                            weChatUserEntity.getCountry(),
                            weChatUserEntity.getGroupid(),
                            weChatUserEntity.getHeadimgurl(),
                            weChatUserEntity.getLanguage(),
                            weChatUserEntity.getNickname(),
                            weChatUserEntity.getProvince(),
                            weChatUserEntity.getQr_scene(),
                            weChatUserEntity.getQr_scene_str(),
                            weChatUserEntity.getRemark(),
                            weChatUserEntity.getSex(),
                            weChatUserEntity.getSubscribe(),
                            weChatUserEntity.getSubscribe_scene(),
                            weChatUserEntity.getSubscribe_time(),
                            weChatUserEntity.getTagid_list(),
                            weChatUserEntity.getUnionid());

                    String Pid = "M" + codeUtils.createRandom(false, 16);
                    System.out.println(Pid);

                    boolean b = accountDao.RegisterLogin(Pid, userPhone, pwd, TimeUtils.getTime(), "123456");
                    System.out.println("是否添加成功平台用户：" + b);
                    if (b == true) {
                        PlatformUserEntity platformUserEntity1 = accountDao.loadByid(Pid);
                        //保存cookies
                        cookiesService.saveCookies(Pid,"PID", response, request);
                        session.setAttribute("PlatformInfo", platformUserEntity1);
                        //1代表成功
                        return "1";


                    } else {
                        //4其他错误
                        return "4";
                    }

                } else {
                    //3验证码错误
                    return "3";
                }
            } else {
                //2手机号错误
                return "2";
            }
        } else {
            return "4";
        }


    }

    public String ResetPassword(String phone, HttpServletResponse response, HttpSession session) {

        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(phone);

        if (platformUserEntity != null) {
            return GetCodeUtils.getCode(phone, session, "4");

        } else {
            //2该用户不存在，请去注册
            return "2";
        }

    }

    public String ChangePassword(String newpwd, String code, String newphone, HttpSession session) {
        System.out.println("开始修改密码！");
        MD5Utils md5Utils = new MD5Utils();
        //再次判断注册手机号已被注册（是否）
        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(newphone);
        if (platformUserEntity == null) {
            //2代表手机未被注册注册
            return "2";
        } else {

            PhoneCodeEntity phoneCode = (PhoneCodeEntity) session.getAttribute(newphone);
            if (phoneCode == null) {
                //5验证码过期
                return "5";
            }
//            System.out.println("phoneCode"+phoneCode);
//            System.out.println(newphone.equals(phoneCode.getPhone()));
            if (newphone.equals(phoneCode.getPhone()) == false) {
                //手机输入错误！
                return "6";
            }


            if (phoneCode.getCode() != null) {
                String mdCode;
                try {
                    mdCode = md5Utils.MD5Encode(code, "utf8");

                } catch (Exception e) {
                    //4修改失败
                    return "4";

                }
                if (mdCode.equals(phoneCode.getCode())) {
                    if (accountDao.updatePwd(platformUserEntity.getP_id(), newpwd)) {
                        //1成功
                        return "1";
                    }
                    //修改失败
                    return "4";
                } else {
                    //验证码错误
                    return "3";
                }
            } else {
                //修改失败
                return "4";
            }
        }
    }
    /**
     * 获取用户
     */
    public PlatformUserEntity loadByMid(String mid){
        return accountDao.loadByid(mid);
    }
    /**
     * 判断openid
     */
    public PlatformUserEntity loadByOenid(String openid){
        return accountDao.loadByOpenid(openid);
    }
}
