package com.afei.elasticsearch.aggstest;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class AggsTest {
    @Resource
    private RestHighLevelClient esClient;

    void test1() throws IOException {
        // 1 构建请求
        SearchRequest request = new SearchRequest("index_test");

        // 构建请求体
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.size(0);

        // 总的aggs
        TermsAggregationBuilder termsBuilder = AggregationBuilders.terms("group_by_color").field("color");

        // 子aggs
        AvgAggregationBuilder avgBuilder = AggregationBuilders.avg("avg_price").field("price");
        MaxAggregationBuilder maxBuilder = AggregationBuilders.max("max_price").field("price");
        MinAggregationBuilder minBuilder = AggregationBuilders.min("min_price").field("price");
        SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_price").field("price");
        termsBuilder.subAggregation(avgBuilder);
        termsBuilder.subAggregation(maxBuilder);
        termsBuilder.subAggregation(minBuilder);
        termsBuilder.subAggregation(sumBuilder);

        builder.aggregation(termsBuilder);
        request.source(builder);

        // 2 执行
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 3 获取结果
        Aggregations aggregations = response.getAggregations();
        Terms group_by_color = aggregations.get("group_by_color");    // 要修改成具体的Terms（Avg、Sum等）
        List<? extends Terms.Bucket> buckets = group_by_color.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String keyAsString = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();

            // 遍历子aggs
            Aggregations subAggregations = bucket.getAggregations();
            Avg avg_price = subAggregations.get("avg_price");
            double value1 = avg_price.getValue();

            Max max_price = subAggregations.get("max_price");
            double value2 = max_price.getValue();

            Min min_price = subAggregations.get("min_price");
            double value3 = min_price.getValue();

            Sum sum_price = subAggregations.get("sum_price");
            double value4 = sum_price.getValue();

        }

    }

}
