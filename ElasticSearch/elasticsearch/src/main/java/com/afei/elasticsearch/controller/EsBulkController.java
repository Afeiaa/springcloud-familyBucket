package com.afei.elasticsearch.controller;


import com.afei.elasticsearch.entity.Product;
import com.afei.elasticsearch.service.EsService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        List<Product> esData = EsService.findEsData(keyword);

        BulkRequest bulkRequest = new BulkRequest();


        return "success！";
    }
}
