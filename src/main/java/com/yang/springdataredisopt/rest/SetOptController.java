package com.yang.springdataredisopt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    /**
     * 向Set中添加一个或多个值，返回添加成功的个数
     */
    public void add() {
        String[] arr = new String[]{"11", "22"};
        this.stringRedisTemplate.opsForSet().add("redisSet", arr);
    }

    /**
     * 删除Set中一个或多个值，返回删除成功的个数
     */
    public void remove() {
        String[] arr = new String[]{"11", "22"};
        this.stringRedisTemplate.opsForSet().remove("redisSet", arr);
    }

    /**
     * 从Set中随机弹出一个元素
     */
    public void pop() {
        this.stringRedisTemplate.opsForSet().pop("redisSet");
    }

    /**
     * 将一个集合中的元素移动到另一个集合
     */
    public void move() {
        this.stringRedisTemplate.opsForSet().move("srcSet", "value", "destSet");
    }

    /**
     * 获取一个Set的元素个数
     */
    public void size() {
        this.stringRedisTemplate.opsForSet().size("redisSet");
    }

    /**
     * 判断一个集合是否包含某个元素
     */
    public void isMember() {
        this.stringRedisTemplate.opsForSet().isMember("redisSet", "jjjjjjjjjjj");
    }

    /**
     * 求两个集合的交集
     */
    public void intersect() {
        this.stringRedisTemplate.opsForSet().intersect("redisSet", "redisSet0");
    }

    /**
     * 求一个集合与多个集合的交集
     */
    public void multiIntersect() {
        Collection<String> collection = new HashSet<>();
        collection.add("11");
        collection.add("22");
        collection.add("33");
        this.stringRedisTemplate.opsForSet().intersect("redisSet", collection);
    }

    /**
     * 获取集合所有元素
     */
    public void members() {
        Set<String> redisSet = this.stringRedisTemplate.opsForSet().members("redisSet");
    }

    /**
     * 将两个集合的交集搬运到第三个集合中，返回成功搬运的元素个数
     */
    public void intersectAndStore() {
        Long aLong = this.stringRedisTemplate.opsForSet().intersectAndStore("redisSet", "redisSet0", "destSet");
    }

    /**
     * 将一个集合与多个集合的交集搬运到第三个集合中，返回成功搬运的元素个数
     */
    public void multiIntersectAndStore() {
        Collection<String> collection = new HashSet<>();
        collection.add("11");
        collection.add("22");
        collection.add("33");
        Long num = this.stringRedisTemplate.opsForSet().intersectAndStore("redisSet", collection, "destSet");
    }

    /**
     * 求两个集合的并集
     */
    public void union() {
        Set<String> union = this.stringRedisTemplate.opsForSet().union("redisSet", "redisSet0");
    }

    /**
     * 将一个集合与多个集合的并集
     */
    public void multiUnion() {
        Collection<String> collection = new HashSet<>();
        collection.add("11");
        collection.add("22");
        collection.add("33");
        Set<String> redisSet = this.stringRedisTemplate.opsForSet().union("redisSet", collection);
    }

    /**
     * 求两个集合的并集
     */
    public void unionAndStore() {
        Long union = this.stringRedisTemplate.opsForSet().unionAndStore("redisSet", "redisSet0", "dest");
    }

    /**
     * 将一个集合与多个集合的并集
     */
    public void multiUnionAndStore() {
        Collection<String> collection = new HashSet<>();
        collection.add("11");
        collection.add("22");
        collection.add("33");
        Long num = this.stringRedisTemplate.opsForSet().unionAndStore("redisSet", collection, "dest");
    }

    /**
     * 求两个集合的差集
     */
    public void difference() {
        Set<String> difference = this.stringRedisTemplate.opsForSet().difference("redisSet", "redisSet0");
    }

    /**
     * 将一个集合与多个集合的差集
     */
    public void multiDifference() {
        Collection<String> collection = new HashSet<>();
        collection.add("11");
        collection.add("22");
        collection.add("33");
        Set<String> redisSet = this.stringRedisTemplate.opsForSet().difference("redisSet", collection);
    }

    /**
     * 求两个集合的差集，并搬运到另一个集合
     */
    public void differenceAndStore() {
        Long union = this.stringRedisTemplate.opsForSet().differenceAndStore("redisSet", "redisSet0", "dest");
    }

    /**
     * 将一个集合与多个集合的差集，并搬运到另一个集合
     */
    public void multiDifferenceAndStore() {
        Collection<String> collection = new HashSet<>();
        collection.add("11");
        collection.add("22");
        collection.add("33");
        Long num = this.stringRedisTemplate.opsForSet().differenceAndStore("redisSet", collection, "dest");
    }

    /**
     * 从集合中随机获取一个元素
     */
    public void randomMember() {
        this.stringRedisTemplate.opsForSet().randomMember("redisSet");
    }

    /**
     * 从集合中随机获取多个元素，结果有可能重复
     */
    public void randomMembers() {
        long count = 6L;
        this.stringRedisTemplate.opsForSet().randomMembers("redisSet", count);
    }

    /**
     * 从集合中随机获取多个元素，结果去重
     */
    public void distinctRandomMembers() {
        long count = 6L;
        this.stringRedisTemplate.opsForSet().distinctRandomMembers("redisSet", count);
    }

    /**
     * 扫描Set，可带筛选条件
     */
    public void scan() {
        Cursor<String> cursor = this.stringRedisTemplate.opsForSet().scan("redisSet", ScanOptions.NONE);
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
        if(cursor != null && !cursor.isClosed()) {
            try {
                cursor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
