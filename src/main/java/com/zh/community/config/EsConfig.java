package com.zh.community.config;


import com.zh.community.dao.DiscussPostMapper;
import com.zh.community.service.DiscussPostRepository;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-14 20:02:00
 */
@Configuration
public class EsConfig {
    @Value("${elasticSearch.url}")
    private String esUrl;

    @Value("${elasticsearch.indices}")
    String esIndices;

    @Autowired
    private DiscussPostMapper discussMapper;

    @Autowired
    private DiscussPostRepository discussRepository;

    @Qualifier("client")
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public EsConfig() {
    }

    //localhost:9200 写在配置文件中就可以了
    @Bean
    RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(esUrl)//elasticsearch地址
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @PostConstruct
    public void init() throws Exception {
        client();

        //建立表
        if(!existsIndex(esIndices)){
            //createIndex(esIndices);
        }else{
            deleteIndex(esIndices);
        }
//
        //把所有帖子（List<DiscussPost>）存入es的discusspost索引（es的索引相当于数据库的表）
        //这个插入顺序是按"最新/热门"来插入都无所谓，因为搜索的时候，会在那里按照"热门"来搜索数据。
        discussRepository.saveAll(discussMapper.selectAllDiscussPosts());
    }

    @PreDestroy
    public void destroy() throws IOException {
        //删除表中数据，销毁表
        deleteIndex(esIndices);
    }
    ////////////////////////////////

    //判断索引是否存在
    public boolean existsIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    //创建索引
    public boolean createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    //删除索引
    public boolean deleteIndex(String index) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        AcknowledgedResponse response = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }
}