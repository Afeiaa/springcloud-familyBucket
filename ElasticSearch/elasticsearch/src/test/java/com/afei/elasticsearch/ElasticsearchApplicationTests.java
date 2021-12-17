package com.afei.elasticsearch;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class ElasticsearchApplicationTests {
    @Resource
    private RestHighLevelClient esClient;

    @Test
    void contextLoads() {
        // 1 构建请求
        CreateIndexRequest request = new CreateIndexRequest("user");

        // 2 执行
        CreateIndexResponse response = null;
        try {
            response = esClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3 获取结果
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);

    }

    @Test
    void test1() {
        // 1 构建请求
        GetIndexRequest request = new GetIndexRequest("test_index");

        // 2 执行
        GetIndexResponse response = null;
        try {
            response = esClient.indices().get(request, RequestOptions.DEFAULT);
            boolean exists = esClient.indices().exists(request, RequestOptions.DEFAULT);
            System.out.println("=====> 索引是否存在：" + exists);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3 获取结果
        System.out.println(response.getAliases());
        System.out.println(response.getMappings());
        System.out.println(response.getSettings());

    }

}
