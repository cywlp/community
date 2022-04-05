package com.zh.community.dao.impl;

import com.zh.community.dao.AlphaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-01 22:01:00
 */
@Repository
@Primary
public class AlphaDaoMybatisImpl implements AlphaDao {
    @Override
    public String select() {
        return "Mybatis";
    }
}
