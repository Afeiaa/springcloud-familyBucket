package com.afei.elasticsearch.controller;


import com.afei.elasticsearch.entity.Product;
import com.afei.elasticsearch.service.EsService;
import com.alibaba.fastjson.JSONObject;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class EsBulkController {
    @Autowired
    private RestHighLevelClient esClient;

    @Autowired
    private EsService esService;

    @GetMapping("/insertToEs/{keyword}")
    public String insert(@PathVariable("keyword") String keyword) throws Exception {
        List<Product> productList = esService.findEsData(keyword);

        BulkRequest bulkRequest = new BulkRequest();

        productList.stream().forEach(item -> {
            bulkRequest.add(new IndexRequest("product").source(JSONObject.toJSONString(item), XContentType.JSON));
        });

        BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return "success！";
    }
}
