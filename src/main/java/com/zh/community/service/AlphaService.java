package com.zh.community.service;

import com.zh.community.dao.AlphaDao;
import com.zh.community.dao.UserMapper;
import com.zh.community.entity.DiscussPost;
import com.zh.community.entity.User;
import com.zh.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-01 22:11:00
 */
@Service
public class AlphaService {

    private static final Logger logger = LoggerFactory.getLogger(AlphaService.class);


    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public AlphaService() {
       // System.out.println("实例化alpha...");
    }

    @PostConstruct
    public void init(){
        //System.out.println("初始化alpha...");
    }

    @PreDestroy
    public void destroy(){
        //System.out.println("销毁alpha...");
    }

    //REQUIRED 支持当前事务（外部事务），如果不存在则创建新事务
    //REQUIRES_NEW 创建一个新事务，并暂停当前事务（外部事物）
    //NESTED 如果当前存在事务（外部事务），则嵌套在该事务中执行（独立的提交和回滚），否则就和REQUIRED一样
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1(){
        //新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.md5("123"+user.getSalt()));
        user.setEmail("alpha@qq.com");
        user.setCreateTime(new Date());
        user.setHeaderUrl("http://images.nowcoder.com/head/99t.png");
        userMapper.insertUser(user);

        //新增帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle("Hello");
        post.setContent("新人报道");
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        int a = 1/0;

        return "ok";
    }

    public Object save2(){

        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                //新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0,5));
                user.setPassword(CommunityUtil.md5("123"+user.getSalt()));
                user.setEmail("beta@qq.com");
                user.setCreateTime(new Date());
                user.setHeaderUrl("http://images.nowcoder.com/head/999t.png");
                userMapper.insertUser(user);

                //新增帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(user.getId());
                post.setTitle("你好");
                post.setContent("新人报道");
                post.setCreateTime(new Date());
                discussPostService.addDiscussPost(post);

                int a = 1/0;
                return "ok";
            }
        });

    }

    //让该线程在多线程环境下被异步调用
    @Async
    public void execyte1(){
        logger.debug("execute1");
    }

//    @Scheduled(initialDelay = 10000, fixedRate = 1000)
//    public void execyte2(){
//        logger.debug("execute2");
//    }

}
