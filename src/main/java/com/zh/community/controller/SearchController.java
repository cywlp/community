package com.zh.community.controller;

import com.zh.community.entity.DiscussPost;
import com.zh.community.entity.Page;
import com.zh.community.service.ElasticsearchService;
import com.zh.community.service.LikeService;
import com.zh.community.service.UserService;
import com.zh.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-14 16:27:00
 */
@Controller
public class SearchController implements CommunityConstant {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model) throws IOException {
        //搜索帖子
        int size = elasticsearchService.searchDiscussPostCount(keyword, 0, 1000);
        List<DiscussPost> searchResult
                = elasticsearchService.searchDiscussPost(keyword, page.getOffset(), page.getLimit());
        System.out.println();
        System.out.println(searchResult.size());
        //聚合数据
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (searchResult != null ){
            for (DiscussPost post : searchResult) {
                Map<String, Object> map = new HashMap<>();
                //帖子
                map.put("post",post);
                //作者
                map.put("user",userService.findUserById(post.getUserId()));
                //点赞数量
                map.put("likeCount",likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId()));
                //帖子
                map.put("post",post);

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        model.addAttribute("keyword",keyword);

        //分页信息
        page.setPath("/search?keyword="+keyword);
        page.setRows(searchResult == null || searchResult.size()==0 ? 0 :size);

        return "site/search";
    }
}
