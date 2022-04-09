package com.zh.community.dao;

import com.zh.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-08 22:02:00
 */
@Mapper
public interface MessageMapper {

    //查询当前会话列表，针对每个会话只返回最新的一条私信
    List<Message> selectConversations(int userId, int offset, int limit);

    //查询当前用户的会话数量
    int selectConversationCount(int userId);

    //查询某个会话所包含的私信列表
    List<Message> selectLetters(String conversationId, int offset, int limit);

    //查询某个会话所包含的私信数量
    int selectLetterCount(String conversationId);

    //查询未读私信数量
    int selectLetterUnreadCount(int userId, String conversationId);

    //新增消息
    int insertMessage(Message message);

    //修改状态
    int updateStatus(List<Integer> ids, int status);
}
