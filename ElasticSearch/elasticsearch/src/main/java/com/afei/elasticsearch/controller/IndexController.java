package com.afei.elasticsearch.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 主页面
    @GetMapping({"/index", "/"})
    public String index() {
        return "index";
    }
}
