package com.zh.community.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-01 22:11:00
 */
@Service
public class AlphaService {

    public AlphaService() {
        System.out.println("实例化alpha...");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化alpha...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁alpha...");
    }

}
