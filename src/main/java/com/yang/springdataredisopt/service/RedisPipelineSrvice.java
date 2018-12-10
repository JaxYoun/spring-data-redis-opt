package com.yang.springdataredisopt.service;

/**
 * @author: Yang
 * @date: 2018/10/21 23:12
 * @description: 
 * 1.Redis的pipeline操作可以将大量命令放在一次请求中执行，【由于Redis采用的是单线程处理机制，当多个请求到来时，会引起排队等待】，在需要批量操作的场景下相比于每次操作都发起一个请求的方式效率成倍提升。
 * 2.值得注意的是：pipeline适合对操作【结果实时性和数据完整性】要求不那么高的批量操作，如果单个操作要求立即就知道执行的结果就不适用了，
 * 同时，对于缓存交互的数据丢失没有补偿机制的情景也不适用。
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
