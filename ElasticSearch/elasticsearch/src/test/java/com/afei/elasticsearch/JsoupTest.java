package com.afei.elasticsearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest
public class JsoupTest {

    @Test
    void test() throws Exception {
        String urlStr = "https://search.jd.com/Search?keyword=" + "java";
        URL url = new URL(urlStr);
        Document document = Jsoup.parse(url, 30000);

        Element j_goodsList = document.getElementById("J_goodsList");
        // System.out.println(j_goodsList);
        Elements liList = j_goodsList.getElementsByTag("li");

        liList.stream().forEach(item -> {
            String img = item.getElementsByTag("img").eq(0).attr("data-lazy-img");
            // String price = item.getElementsByAttribute("data-price").text();
            String price = item.getElementsByClass("p-price").text();
            String shop = item.getElementsByClass("curr-shop").attr("title");
            String title = item.getElementsByClass("p-name").text();
            System.out.println(title);
        });

    }

    @Test
    void test1() throws Exception {
        String urlStr = "http://10.200.33.184/cloudResourceMgt/aliyunMgt";
        URL url = new URL(urlStr);
        Document document = Jsoup.parse(url, 30000);

        String text = document.getElementsByClass("custom-antd-table").text();
        System.out.println("=================" + text);

    }
}
