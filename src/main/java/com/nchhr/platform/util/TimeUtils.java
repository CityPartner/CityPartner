package com.nchhr.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {


    public static String getTime(){
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = df.format(now);
        System.out.println(nowtime);
        return nowtime;
    }
}
