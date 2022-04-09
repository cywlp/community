package com.zh.community.controller;

import com.zh.community.entity.Comment;
import com.zh.community.entity.DiscussPost;
import com.zh.community.entity.Page;
import com.zh.community.entity.User;
import com.zh.community.service.CommentService;
import com.zh.community.service.DiscussPostService;
import com.zh.community.service.UserService;
import com.zh.community.util.CommunityConstant;
import com.zh.community.util.CommunityUtil;
import com.zh.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-08 17:00:00
 */

@Controller
@RequestMapping("/comment")
public class CommentController implements CommunityConstant {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        return "redirect:/discuss/detail/"+discussPostId;
    }

}
