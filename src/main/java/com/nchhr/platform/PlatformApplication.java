package com.nchhr.platform;

import com.nchhr.platform.util.WeChatUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@MapperScan("com.nchhr.platform.dao")
public class PlatformApplication extends SpringBootServletInitializer implements WebApplicationInitializer
{

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);

        WeChatUtil.CCSR();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(PlatformApplication.class);
    }
}
