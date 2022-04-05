package com.zh.community.controller;

import com.zh.community.entity.DiscussPost;
import com.zh.community.entity.Page;
import com.zh.community.entity.User;
import com.zh.community.service.DiscussPostService;
import com.zh.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-02 21:33:00
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        //方法调用前，SpringMVC会自动实例化Model和Page，并将Page注入Model
        //所以在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> discussPostList = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (discussPostList != null) {
            for (DiscussPost discussPost : discussPostList) {
                Map<String, Object> map = new HashMap<>();
                map.put("discussPost", discussPost);
                User user = userService.findUserById(discussPost.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }


}
