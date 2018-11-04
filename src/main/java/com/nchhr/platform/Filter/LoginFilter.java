package com.nchhr.platform.Filter;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {



    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        //如果没有登录
//        String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/", 1), req.getRequestURI().length());
        String requestURI = req.getRequestURI();
        System.out.println("RequestURI：" + requestURI);
//        System.out.println("路径："+req.getContextPath());
////        boolean ss = requestURI.contains("**/index**");
//        System.out.println("80:"+requestURI);
        //访问除login.jsp（登录页面）和验证码servlet之外的jsp/servlet都要进行验证
        if (    !(req.getContextPath()+"/").contains(requestURI)
                && requestURI.contains(req.getContextPath())
                && !requestURI.contains("/images")
                && !requestURI.contains("/funds")
                && !requestURI.contains("/js")
                && !requestURI.contains("/style")
                && !requestURI.contains("/wechat_redirect")
                && !requestURI.contains("/custom-js")
                && !requestURI.contains("/custom-css")
                && !requestURI.contains("/goods")
                && !requestURI.contains("/wechatuser")
                && !requestURI.contains("/register")
                && !requestURI.contains("/index")
                && !requestURI.contains("/getPlatformInfo")
                && !requestURI.contains("/login")
                && !requestURI.contains("/RegisterLogin")
                && !requestURI.contains("/deleteCode")
                && !requestURI.contains("/getCode")
                && !requestURI.contains("/usr/local/upload/")
                && !requestURI.contains("/indexImg")
                && !requestURI.contains("/ResetPassword")
                && !requestURI.contains("/ChangePassword")
                && !requestURI.contains("/RegistLogin")
                //页面
                && !requestURI.contains("/login.html")
                && !requestURI.contains("/404.html")
                && !requestURI.contains("/register.html")
                && !requestURI.contains("/recover.html")
                //测试接口
                && !requestURI.contains("/test")
                ) {
            //判断cookies中是否有用户信息，如果没有则重定向到登录页面
            String PID = "" ;
            Cookie[] cookies = req.getCookies();
            HttpSession session = req.getSession();
            if (cookies == null ){
                res.sendRedirect( req.getContextPath()+"/login.html");
                return;
            }
            else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("PID")) {
                        PID = cookie.getValue();
                    }
                }
            }
            System.out.println("=====进入过滤器=====");
            System.out.println("PID:"+PID);
            if ( PID == null || PID.equals("")) {

                res.sendRedirect( req.getContextPath()+"/login.html");
                return;
            }else {
                if (session.getAttribute("PlatformInfo") == null) {

                    req.getRequestDispatcher( req.getContextPath()+"/getPlatformInfo").forward(request,response);
                }
            }


        }

        chain.doFilter(request, response);

    }

    public void destroy() {

    }




}
