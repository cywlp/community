package com.zh.community.service;

import com.zh.community.dao.UserMapper;
import com.zh.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-02 21:26:00
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
