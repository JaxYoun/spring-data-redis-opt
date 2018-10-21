package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.entity.User;
import com.yang.springdataredisopt.service.RedisTemplateBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author: Yang
 * @date: 2018/10/22 00:56
 * @description:
 */
@Service
public class RedisTemplateBasicImpl implements RedisTemplateBasicService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void templateInstantCount() {
        this.stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.aa();
        String name = this.stringRedisTemplate.getValueSerializer().getClass().getName();
        System.out.println("原始名：" + name);
        String name0 = this.stringRedisTemplate.getValueSerializer().getClass().getName();
        System.out.println("名：" + name0);
    }

    private void aa() {
        Set<String> myKay = this.stringRedisTemplate.keys("myKay");
        String name = this.stringRedisTemplate.getValueSerializer().getClass().getName();
        System.out.println("aa原始名：" + name);
        this.bb();
        String name2 = this.stringRedisTemplate.getValueSerializer().getClass().getName();
        System.out.println("aa名kkk：" + name2);
        System.out.println(myKay.size());
    }

    private void bb() {
        Set<String> myKay = this.stringRedisTemplate.keys("myKay");
        String name = this.stringRedisTemplate.getValueSerializer().getClass().getName();
        System.out.println("bb原始名：" + name);
        this.stringRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        String name0 = this.stringRedisTemplate.getValueSerializer().getClass().getName();
        System.out.println("bb名：" + name0);
        System.out.println(myKay.size());
    }
}
