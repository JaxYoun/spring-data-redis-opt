package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.service.RedisTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Yang
 * @date: 2018/10/22 01:40
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTypeServiceImplTest {

    @Autowired
    private RedisTypeService redisTypeService;

    @Test
    public void typeTest() {
        this.redisTypeService.insertAndGetUserAsObject();
    }

}