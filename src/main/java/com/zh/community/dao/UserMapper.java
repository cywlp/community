package com.zh.community.dao;

import com.zh.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-02 16:53:00
 */
@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String name);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id,int status);

    int updateHeader(int id,String headerUrl);

    int updatePassword(int id,String password);
}
