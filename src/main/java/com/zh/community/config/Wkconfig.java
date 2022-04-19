package com.zh.community.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-17 20:42:00
 */
@Configuration
public class Wkconfig {

    private static final Logger logger = LoggerFactory.getLogger(Wkconfig.class);

    @Value("${wk.image.storage}")
    private String wkImageStorage;

    @PostConstruct
    public void init(){
        //创建WK图片目录
        File file = new File(wkImageStorage);
        if (!file.exists()){
            file.mkdir();
            logger.info("创建WK图片目录：" + wkImageStorage);
        }
    }
}
