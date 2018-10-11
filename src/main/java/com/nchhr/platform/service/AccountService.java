package com.nchhr.platform.service;

import com.nchhr.platform.dao.AccountDao;
import com.nchhr.platform.entity.PlatformUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AccountService {

    @Resource
    AccountDao accountDao;

    @Autowired
    CookiesService cookiesService;

    public String login(String phone, String pwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        PlatformUser platformUser = accountDao.loadByPhone(phone);
        if (platformUser == null) {
            //用户不存在
            return "2";
        }
        if (platformUser != null) {
            if (pwd.equals(platformUser.getPassword())) {
                //保存session
                session.setAttribute("PlatformInfo", platformUser);
                //保存cookies
                cookiesService.saveCookies(platformUser.getP_id(), response, request);
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

}
