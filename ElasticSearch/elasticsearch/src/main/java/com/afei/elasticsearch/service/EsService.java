package com.afei.elasticsearch.service;

import com.afei.elasticsearch.entity.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsService {

    public static List<Product> findEsData(String keyword) throws Exception {
        String urlStr = "https://search.jd.com/Search?keyword=" + keyword;
        URL url = new URL(urlStr);
        Document document = Jsoup.parse(url, 30000);

        Element j_goodsList = document.getElementById("J_goodsList");
        // System.out.println(j_goodsList);
        Elements liList = j_goodsList.getElementsByTag("li");

        List<Product> productList = new ArrayList<>();
        liList.stream().forEach(item -> {
            String img = item.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = item.getElementsByClass("p-price").text();
            String shop = item.getElementsByClass("curr-shop").attr("title");
            String title = item.getElementsByClass("p-name").text();

            Product product = new Product();
            product.setImg(img);
            product.setPrice(price);
            product.setShop(shop);
            product.setTitle(title);
            productList.add(product);
        });
        return productList;
    }

}
