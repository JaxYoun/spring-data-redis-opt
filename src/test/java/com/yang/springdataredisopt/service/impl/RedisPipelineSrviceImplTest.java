package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.service.RedisPipelineSrvice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Yang
 * @date: 2018/10/22 00:17
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisPipelineSrviceImplTest {

    @Autowired
    private RedisPipelineSrvice redisPipelineSrvice;

    @Test
    public void redisPipelineTest() {
        this.redisPipelineSrvice.redisPipeline();
    }

    @Test
    public void redisPipelineInsertInstanceTest() {
        this.redisPipelineSrvice.redisPipelineVaExecute();
    }


}