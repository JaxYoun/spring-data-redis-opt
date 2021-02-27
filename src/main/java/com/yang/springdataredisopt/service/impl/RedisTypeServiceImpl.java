package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.entity.Student;
import com.yang.springdataredisopt.entity.User;
import com.yang.springdataredisopt.service.RedisTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * @date: 2018/10/22 01:29
 * @description:
 */
@Service
public class RedisTypeServiceImpl implements RedisTypeService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void insertAndGetUserAsString() {
        String myUser = this.stringRedisTemplate.opsForValue().get("myUser");
        System.out.println(myUser);
    }

    @Override
    public void insertAndGetUserAsObject() {
        User usre = User.builder().id(19).name("papi").build();
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        this.redisTemplate.opsForValue().set("myUser", usre);
        Object myUser = this.redisTemplate.opsForValue().get("myUser");
        User mmm = (User) myUser;
        System.out.println(mmm.getId());

        Student student = null;//Student.builder().clazz("rs2").teacher("ding").build();
        this.redisTemplate.opsForValue().set("myStudent", student);
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Student.class));
        Object myStudent = this.redisTemplate.opsForValue().get("myStudent");
        Student student1 = (Student) myStudent;
        System.out.println(student1.getTeacher());
    }
}
