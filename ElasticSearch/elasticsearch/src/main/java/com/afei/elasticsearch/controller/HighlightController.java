package com.afei.elasticsearch.controller;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HighlightController {

    @Resource
    private RestHighLevelClient esClient;

    @GetMapping("/esHighlightSearch/{keyword}")
    public List<Map<String, Object>> esHighlightSearch(@PathVariable("keyword") String keyword) throws IOException {
        SearchRequest request = new SearchRequest("product");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("title", keyword));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(false)
                .field("title")
                .preTags("<span style='color: red;'>")
                .postTags("</span>");
        builder.highlighter(highlightBuilder);
        builder.from(0);
        builder.size(10);

        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 得到高亮结果
        SearchHit[] hits = response.getHits().getHits();
        List<Map<String, Object>> productList = new ArrayList<>();

        for (SearchHit hit : hits) {
            hit.getId();
            hit.getSourceAsString();

            // _source转map
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            String newTitle = "";
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields.containsKey("title")) {
                HighlightField highlightTitle = highlightFields.get("title");
                Text[] fragments = highlightTitle.fragments();
                for (Text fragment : fragments) {
                    newTitle += fragment.toString();
                }
            }
            sourceAsMap.put("title", newTitle);
            productList.add(sourceAsMap);
        }

        return productList;
    }
}
