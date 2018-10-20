package com.yang.springdataredisopt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Yang
 * @date: 2018/10/20 11:55
 * @description: Hash类型对象操作
 */
@RestController
@RequestMapping("/hashOpt")

public class HashOptController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void init() {
        HashOperations<String, Object, Object> hashOpt = this.stringRedisTemplate.opsForHash();
        hashOpt.put("redisHash", "name", "tom");
        hashOpt.put("redisHash", "age", "26");
        hashOpt.put("redisHash", "class", "6");

        Map<String, Object> map = new HashMap<>(3);
        map.put("name", "jerry");
        map.put("age", "26");
        map.put("class", "7");
        hashOpt.putAll("redisHash1", map);
    }

    /**
     * 删除Hash对象的单个【字段名和值】
     */
    public void delete() {
        //删除之对象下的单个hash键值对
        this.stringRedisTemplate.opsForHash().delete("redisHash", "name");
        //获取值对象下的说有键值对
        Map<Object, Object> map = this.stringRedisTemplate.opsForHash().entries("redisHash");
        map.forEach((k, v) -> System.out.println(k.toString() + v.toString()));
    }

    /**
     * 判断Hash对象中是否包含某个字段
     */
    public void hasKey() {
        Boolean exist = this.stringRedisTemplate.opsForHash().hasKey("redisHash", "name");
        System.out.println(exist);
    }

    /**
     * 获取Hash对象中的指定字段值
     */
    public void get() {
        Object o = this.stringRedisTemplate.opsForHash().get("redisHash", "age");
        System.out.println(o);
    }

    /**
     * 批量获取Hash对象中的多个字段值
     */
    public void multiGet() {
        Set<Object> hkeySet = new HashSet<>();
        hkeySet.add("name");
        hkeySet.add("age");
        hkeySet.add("class");
        this.stringRedisTemplate.opsForHash().multiGet("redisHash", hkeySet).forEach(System.out::println);
    }

    /**
     * 给Hash对象中指定字段增长数字（支持long和double）
     */
    public void increment() {
        this.stringRedisTemplate.opsForHash().increment("redisHash", "age", 100L);
        this.stringRedisTemplate.opsForHash().increment("redisHash", "age", 100D);
    }

    /**
     * 获取hash对象的所有字段名
     */
    public void keys() {
        this.stringRedisTemplate.opsForHash().keys("redisHash");
    }

    /**
     * 获取Hash对象的所有字段值
     */
    public void values() {
        this.stringRedisTemplate.opsForHash().values("redisHash");
    }

    /**
     * 获取Hash对象中的字段数量
     */
    public void size() {
        this.stringRedisTemplate.opsForHash().size("redisHash");
    }

    /**
     * 将字段和值批量加入到Hash对象中
     */
    public void putAll() {
        Map<String, String> map = new HashMap<>(4);
        map.put("name", "yang");
        map.put("age", "30");
        map.put("class", "2");
        map.put("add", "cuit");
        this.stringRedisTemplate.opsForHash().putAll("redisHash2", map);
    }

    /**
     * 向Hash对象插入一个字段和值（如果有就更新）
     */
    public void put() {
        this.stringRedisTemplate.opsForHash().put("redisHash2", "sex", "man");
    }

    /**
     * 向Hash对象插入一个字段及值（如果不存在）
     */
    public void putIfAbsent() {
        this.stringRedisTemplate.opsForHash().putIfAbsent("redisHash2", "work", "coder");
    }

    /**
     * 获取Hash对象的所有字段名和值
     */
    public void entries() {
        Map<Object, Object> redisHash2 = this.stringRedisTemplate.opsForHash().entries("redisHash2");
    }

    /**
     * 扫描Hash对象内的字段，可以带上扫描条件（类似与搜索）,返回结果是一个游标，可以用Map.Entry进行遍历
     */
    public void scan() {
        Cursor<Map.Entry<Object, Object>> cursor = this.stringRedisTemplate.opsForHash().scan("redisHash2", ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            entry.getKey();
            entry.getValue();
        }
        try {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void kk(Object k) {
        System.out.println(k.toString());
    }


}
