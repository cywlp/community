package com.zh.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-01 22:22:00
 */
@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
