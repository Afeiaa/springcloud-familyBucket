package com.afei.elasticsearch.config;

import com.afei.elasticsearch.utils.EsHostInfo;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
public class MyEsConfig {

//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        return new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost(EsHostInfo.HOST, Integer.parseInt(EsHostInfo.PORT), "http")));
//    }

//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//    }


}
