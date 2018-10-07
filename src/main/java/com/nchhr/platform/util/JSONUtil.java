package com.nchhr.platform.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONUtil {

    public static String getJSONByURL(String URL) {
        URL url = null;
        HttpURLConnection httpConn = null;
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(URL);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ex) {
            //
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                //
            }
        }
        return sb.toString();
    }
}
