package com.zh.community;

import com.zh.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-07 20:59:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSentiviFilter() {
        String text = "赌博嫖娼开票fabc";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}
