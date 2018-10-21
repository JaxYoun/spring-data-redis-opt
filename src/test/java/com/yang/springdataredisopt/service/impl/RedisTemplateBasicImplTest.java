package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.service.RedisPipelineSrvice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Yang
 * @date: 2018/10/22 00:59
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateBasicImplTest {

    @Autowired
    private RedisTemplateBasicImpl redisTemplateBasic;

    @Autowired
    private RedisPipelineSrvice redisPipelineSrvice;

    @Test
    public void instantCountTest() {
        this.redisTemplateBasic.templateInstantCount();
        this.redisPipelineSrvice.redisPipelineVaExecute();
    }

}