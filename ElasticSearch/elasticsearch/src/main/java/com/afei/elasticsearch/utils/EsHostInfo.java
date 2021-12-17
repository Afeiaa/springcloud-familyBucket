package com.afei.elasticsearch.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EsHostInfo implements InitializingBean {

    @Value("${afei.es.host}")
    private String host;

    @Value("${afei.es.port}")
    private String port;


    public static String HOST;
    public static String PORT;

    @Override
    public void afterPropertiesSet() throws Exception {
        HOST= host;
        PORT = port;
    }
}
