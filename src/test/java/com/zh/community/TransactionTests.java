package com.zh.community;

import com.zh.community.dao.DiscussPostMapper;
import com.zh.community.dao.LoginTicketMapper;
import com.zh.community.dao.UserMapper;
import com.zh.community.entity.DiscussPost;
import com.zh.community.entity.LoginTicket;
import com.zh.community.entity.User;
import com.zh.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-02 17:46:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TransactionTests {

    @Autowired
    private AlphaService alphaService;

    @Test
    public void testSave1(){
        Object o = alphaService.save1();
        System.out.println(o);
    }

    @Test
    public void testSave2(){
        Object o = alphaService.save2();
        System.out.println(o);
    }
}
