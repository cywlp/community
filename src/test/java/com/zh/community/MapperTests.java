package com.zh.community;

import com.zh.community.dao.DiscussPostMapper;
import com.zh.community.dao.UserMapper;
import com.zh.community.entity.DiscussPost;
import com.zh.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
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
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("12345");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser() {
        int rows = userMapper.updateStatus(150,1);
        System.out.println(rows);

        rows=userMapper.updateHeader(150,"http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows=userMapper.updatePassword(150,"123");
        System.out.println(rows);
    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
    }
}
