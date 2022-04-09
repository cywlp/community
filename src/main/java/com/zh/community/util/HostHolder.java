package com.zh.community.util;

import com.zh.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author :珠代
 * @description :持有用户的信息，用于代替session对象
 * @create :2022-04-06 19:50:00
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users =new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
