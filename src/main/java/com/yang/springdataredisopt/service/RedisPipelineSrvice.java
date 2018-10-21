package com.yang.springdataredisopt.service;

/**
 * @author: Yang
 * @date: 2018/10/21 23:12
 * @description:
 */
public interface RedisPipelineSrvice {

    /**
     * 通过Template.executePipline()方法执行pipeline，执行结果集可以返回到方法级别
     */
    void redisPipeline();


    /**
     * 通过Template.execute()方法执行pipeline，执行结果集只能在匿名内部类中处理
     */
    void redisPipelineVaExecute();

}
