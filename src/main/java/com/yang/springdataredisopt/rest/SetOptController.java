package com.yang.springdataredisopt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * @date: 2018/10/20 11:53
 * @description:
 */
@RestController
@RequestMapping("/setOpt")
public class SetOptController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



}
