package com.yang.springdataredisopt.service;

/**
 * @author: Yang
 * @date: 2018/10/22 01:21
 * @description:
 */
public interface RedisTypeService {

    void insertAndGetUserAsString();

    /**
     * 自定义类型的数据不能通过Object的模板来存取，必须为对应的类型设置各自的序列化组件
     * 所以在封装自定义类型的Redis工具类时，必须在方法内实例化模板，且模板的序列化组件必须泛型化
     * 并且存和取的序列化组件要一致
     */
    void insertAndGetUserAsObject();

}
