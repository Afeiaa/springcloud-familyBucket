package com.afei.rabbitmq.util;


// 常量类
public class ConstantProperties {
    /**
     *  延迟交换机队列   -> delay包下测试
     */
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange_name";
    public static final String DELAY_ROUTING_KEY = "delay_routing_key";
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";

    /**
     *  发布确认(高级)   -> confirm包下测试
     */
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange_name";
    public static final String CONFIRM_ROUTING_KEY = "confirm_routing_key";
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue_name";

    /**
     *  优先级队列   -> priority包下测试
     */
    public static final String PRIORITY_EXCHANGE_NAME = "priority_exchange_name";
    public static final String PRIORITY_ROUTING_KEY = "priority_routing_key";
    public static final String PRIORITY_QUEUE_NAME = "priority_queue_name";

}
