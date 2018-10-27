package com.nchhr.platform.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookiesService {

    public boolean saveCookies(String MID,String cookiesName, HttpServletResponse response, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {

                Cookie WXIDCookie = new Cookie(cookiesName, MID);

                WXIDCookie.setMaxAge(86400);//设置cookie生存时间：
                response.addCookie(WXIDCookie);// 添加cookie：
            } else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(cookiesName)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                Cookie WXIDCookie = new Cookie(cookiesName, MID);
                WXIDCookie.setMaxAge(86400);//设置cookie生存时间：
                response.addCookie(WXIDCookie);// 添加cookie：
            }

        } catch (Exception e) {
            return false;

        }
        return true;
    }

    public boolean clear(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("MID")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return true;
    }

    public boolean alreadyCookies(HttpServletRequest request,String cookiesName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiesName)) {

                    return true;
                }
                return false;
            }
            return false;
        }

    }
    public String printCookies(HttpServletRequest request,String cookiesName){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "0";
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiesName)) {

                    return cookie.getValue();
                }
                return "0";
            }
            return "0";
        }
    }
}
