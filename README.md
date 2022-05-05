# community
涉及技术：Springboot、SpringMVC、Mybatis、MySQL、Redis、Kafka、SpringSecurity、Elasticsearch、Kaptcha、Quartz

主要功能：

  1、登录注册功能：使用kaptcha去生成验证码，使用邮件完成注册，Redis优化验证码的保存，解决分布式session问题
  
  2、使用拦截器拦截用户请求，将用户信息绑定在ThreadLocal上
  
  3、构建Trie数据结构，实现对发表帖子评论的敏感词过滤
  
  4、支持对帖子评论，也支持对评论进行回复
  
  5、利用AOP对service的业务代码实现日志记录
  
  6、利用Redis的zset并结合Redis实现点赞关注的功能
  
  7、点赞关注后的系统通知，实时性不需要特别高，使用kafka实现异步的发送系统通知
  
  8、使用ElasticSearch实现对帖子的搜索功能，以及结果的高亮显示
  
  9、SpringQuartz实现定时任务，完成热门帖子的分数计算模块
  
  10、使用本地缓存Caffeine缓存热门帖子优化热门帖子页面，极大的提高了QPS（10 - 200）
