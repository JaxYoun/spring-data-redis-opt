package com.yang.springdataredisopt.rest;

import com.yang.springdataredisopt.entity.Student;
import com.yang.springdataredisopt.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * @date: 2021/2/27 14:25
 * @description:
 */
@RestController
@RequestMapping("/serializer")
public class SerializerController {

    @Resource
    private RedisTemplate<String, Object> genericRedisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get")
    public Object get() {
        List<LocalDateTime> kk = new ArrayList<>(2);
        kk.add(LocalDateTime.now());
        kk.add(LocalDateTime.now());

        User user = User.builder().id(1).name("yang").timeList(kk).build();
        String key = String.valueOf(System.currentTimeMillis());
        String hkey = String.valueOf("hkey" + System.currentTimeMillis());

        genericRedisTemplate.opsForHash().put(key, hkey, user);
        User obj = (User) genericRedisTemplate.opsForHash().get(key, hkey);
        return obj;
    }

    @GetMapping("/get0")
    public Object get0() {
        List<Student> kk = new ArrayList<>(2);
        kk.add(new Student());
        kk.add(new Student());

        User user = User.builder().id(1).name("yang").studentList(kk).build();
        String key = String.valueOf(System.currentTimeMillis());
        String hkey = String.valueOf("hkey" + System.currentTimeMillis());

        genericRedisTemplate.opsForHash().put(key, hkey, user);
        User obj = (User) genericRedisTemplate.opsForHash().get(key, hkey);
        return obj;
    }

    @GetMapping("/init")
    public void init() {

    }

}
