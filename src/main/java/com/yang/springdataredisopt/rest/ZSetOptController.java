package com.yang.springdataredisopt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Yang
 * @date: 2018/10/20 15:04
 * @description: zSet操作
 */
@RestController
@RequestMapping("/zSetOpt")
public class ZSetOptController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向zSet中添加单个元素Long
     */
    public void longAdd() {
        Boolean add = this.stringRedisTemplate.opsForZSet().add("", "", 12L);
    }

    /**
     * 向zSet中添加单个元素Double
     */
    public void doubleAdd() {
        Boolean add = this.stringRedisTemplate.opsForZSet().add("", "", 12D);
    }

    /**
     * 向zSet中添加多个元素Double
     */
    public void multiAdd() {
        ZSetOperations.TypedTuple<String> t0 = new DefaultTypedTuple<>("zset-5", 9.6D);
        ZSetOperations.TypedTuple<String> t1 = new DefaultTypedTuple<>("zset-5", 9.6D);
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        set.add(t0);
        set.add(t1);
        Long tuples = this.stringRedisTemplate.opsForZSet().add("tuples", set);
    }

    /**
     * 删除排序集合中的一到多个元素
     */
    public void remove() {
        String[] arr = new String[]{"11", "22"};
        Long redisSet = this.stringRedisTemplate.opsForZSet().remove("redisSet", arr);
    }

    /**
     * 增加指定元素的分数，返回增加后的得分
     */
    public void incrementScore() {
        Double redisSet = this.stringRedisTemplate.opsForZSet().incrementScore("redisSet", "myValue", 10D);
    }

    /**
     * 返回指定元素的升序排名
     */
    public void rank() {
        Long rank = this.stringRedisTemplate.opsForZSet().rank("redisSet", "myValue");
    }

    /**
     * 返回指定元素的降序排名
     */
    public void reverseRank() {
        Long rank = this.stringRedisTemplate.opsForZSet().reverseRank("redisSet", "myValue");
    }

    /**
     * 获取升序排名区间内的所有元素，(排名参数可以为负数，表示倒数)
     */
    public void range() {
        Set<String> redisSet = this.stringRedisTemplate.opsForZSet().range("redisSet", 1L, 22L);
    }

    /**
     * 获取升序排名区间内的所有元素之二元组，(排名参数可以为负数，表示倒数)
     */
    public void rangeWithScores() {
        Set<ZSetOperations.TypedTuple<String>> redisSet = this.stringRedisTemplate.opsForZSet().rangeWithScores("redisSet", 1L, 22L);
        Iterator<ZSetOperations.TypedTuple<String>> iterator = redisSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<String> next = iterator.next();
            next.getScore();
            next.getValue();
        }
    }

    /**
     * 获取指定分数区间内的所有元素
     */
    public void rangeByScore() {
        Set<String> redisSet = this.stringRedisTemplate.opsForZSet().rangeByScore("redisSet", 1.2D, 22.9D);
    }

    /**
     * 获取指定分数区间内的所有元素，然后再从结果集里面从偏移量处取出count个元素
     */
    public void rangeByScoreOfCount() {
        long offset = 8;
        long count = 3;
        Set<String> redisSet = this.stringRedisTemplate.opsForZSet().rangeByScore("redisSet", 1.2D, 22.9D, offset, count);
    }

    /**
     * 获取升序排名区间内的所有元素之二元组，(排名参数可以为负数，表示倒数)
     */
    public void rangeByScoreWithScores() {
        Set<ZSetOperations.TypedTuple<String>> redisSet = this.stringRedisTemplate.opsForZSet().rangeByScoreWithScores("redisSet", 1.2D, 22.9D);
        Iterator<ZSetOperations.TypedTuple<String>> iterator = redisSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<String> next = iterator.next();
            next.getScore();
            next.getValue();
        }
    }

    /**
     * 获取升序排名区间内的所有元素之二元组，(排名参数可以为负数，表示倒数)
     */
    public void rangeByScoreWithScoresOfCount() {
        long offset = 8;
        long count = 3;
        Set<ZSetOperations.TypedTuple<String>> redisSet = this.stringRedisTemplate.opsForZSet().rangeByScoreWithScores("redisSet", 1.2D, 22.9D, offset, count);
        Iterator<ZSetOperations.TypedTuple<String>> iterator = redisSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<String> next = iterator.next();
            next.getScore();
            next.getValue();
        }
    }

    /**
     * 返回指定分数区间的元素个数
     */
    public void count() {
        this.stringRedisTemplate.opsForZSet().count("redisSet", 12D, 33D);

    }

    /**
     * 返回Set中的元素个数
     */
    public void size() {
        this.stringRedisTemplate.opsForZSet().size("redisSet");

    }

    /**
     * 返回Set中的元素个数
     */
    public void zCard() {
        this.stringRedisTemplate.opsForZSet().zCard("redisSet");

    }

    /**
     * 返回Set中指定元素的得分
     */
    public void score() {
        this.stringRedisTemplate.opsForZSet().score("redisSet", "yangValue");

    }

    /**
     * 删除指定升序排名区间中的元素
     */
    public void removeRange() {
        this.stringRedisTemplate.opsForZSet().removeRange("redisSet", 2L, 6L);

    }

    /**
     * 删除指定分数区间中的元素
     */
    public void removeRangeByScore() {
        this.stringRedisTemplate.opsForZSet().removeRangeByScore("redisSet", 2D, 6D);

    }

    /**
     * 求两个有序集合并集，并转存到第三个有序集合
     */
    public void unionAndStore() {
        this.stringRedisTemplate.opsForZSet().unionAndStore("redisSet", "redisSet0", "destSet");

    }

    /**
     * 求一个有序集合与多个有序集合的并集，并转存到第三个有序集合
     */
    public void multiUnionAndStore() {
        Collection<String> set = new HashSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        set.add("44");
        Long aLong = this.stringRedisTemplate.opsForZSet().unionAndStore("redisSet", set, "destSet");
    }

    /**
     * 求两个有序集合并集，并转存到第三个有序集合
     */
    public void intersectAndStore() {
        this.stringRedisTemplate.opsForZSet().intersectAndStore("redisSet", "redisSet0", "destSet");

    }

    /**
     * 求一个有序集合与多个有序集合的并集，并转存到第三个有序集合
     */
    public void multiIntersectAndStore() {
        Collection<String> set = new HashSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        set.add("44");
        Long aLong = this.stringRedisTemplate.opsForZSet().intersectAndStore("redisSet", set, "destSet");
    }

    /**
     * 扫描一个排序集合
     */
    public void scan() {
        this.stringRedisTemplate.opsForZSet().scan("redisSet", ScanOptions.NONE);
    }

}
