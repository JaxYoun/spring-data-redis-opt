package com.yang.springdataredisopt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Yang
 * @date: 2018/10/16 22:15
 * @description:
 */
@RestController
@RequestMapping("/valueOpt")
public class ValueOptController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 单纯设值，取值
     */
    public void set() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        //1.存值
        valueOperations.set("valueOpt", "111");
        //2.取值
        System.out.println(valueOperations.get("valueOpt"));
    }

    /**
     * 将旧值取出返回，同时将值更新为新值
     */
    public void getAndSet() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        //1.更新操作
        valueOperations.getAndSet("valueOpt", "new111");
        //2.取值
        System.out.println(valueOperations.get("valueOpt"));
    }

    /**
     * 设值同时指定过期时间
     */
    public void setWithExpire() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        //1.存值
        valueOperations.set("valueOpt", "111", 5L, TimeUnit.SECONDS);
        //2.取值，过期后返回null
        System.out.println(valueOperations.get("valueOpt"));
    }

    /**
     * 如果key对应有值，就将旧值的指定偏移位置开始替换掉
     */
    public void setAndReplace() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        //1.存值
        valueOperations.set("valueOpt", "123456789");
        valueOperations.set("valueOpt", "aaa", 5);
        //2.取值
        System.out.println(valueOperations.get("valueOpt"));
    }

    /**
     * 如果key对应无值，则存入并返回true，如果有就返回false
     */
    public void setIfAbsent() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        //1.存值
        valueOperations.set("valueOpt", "123456789");
        boolean success = valueOperations.setIfAbsent("valueOpt", "aaa");
        System.out.println(success);
        //2.取值
        System.out.println(valueOperations.get("valueOpt"));
    }

    /**
     * 批量存、批量取
     */
    public void multiSetAndGet() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        Map<String, String> maps = new HashMap<>(3);
        maps.put("multi1", "multi1");
        maps.put("multi2", "multi2");
        maps.put("multi3", "multi3");

        valueOperations.multiSet(maps);
        List<String> keys = new ArrayList<>(3);
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");

        valueOperations.multiGet(keys).forEach(System.out::println);
    }

    /**
     * 如果服务器中没有重复键值对就存入并返回true，如果有就返回false
     */
    public void multiSetIfAbsent() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        Map<String, String> maps = new HashMap<>(3);
        maps.put("multi11", "multi11");
        maps.put("multi22", "multi22");
        maps.put("multi33", "multi33");
        Map<String, String> maps2 = new HashMap<>(3);
        maps2.put("multi1", "multi1");
        maps2.put("multi2", "multi2");
        maps2.put("multi3", "multi3");
        System.out.println(valueOperations.multiSetIfAbsent(maps));
        System.out.println(valueOperations.multiSetIfAbsent(maps2));
    }

    /**
     * 整数的递增
     */
    public void longIncreament() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        valueOperations.set("increament", "1");
        System.out.println(valueOperations.get("increament"));

        valueOperations.increment("increament", 3L);
        System.out.println(valueOperations.get("increament"));
    }

    /**
     * 小数的递增
     */
    public void doubleIncreament() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        valueOperations.set("doubleIncreament", "1.1");
        System.out.println(valueOperations.get("doubleIncreament"));

        valueOperations.increment("doubleIncreament", 3D);
        System.out.println(valueOperations.get("doubleIncreament"));
    }

    /**
     * 如果键不存在，就会新插入键再赋值，如果键存在则直接将新值追加到旧值后
     */
    public void append() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        valueOperations.append("append", "append");
        System.out.println(valueOperations.get("append"));

        valueOperations.append("doubleIncreament", "3D");
        System.out.println(valueOperations.get("doubleIncreament"));
    }

    /**
     * 截取值的指定偏移量间的字符串
     */
    public void rangeGet() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        valueOperations.append("append", "append");
        System.out.println(valueOperations.get("append", 0L, 3L));

        //当有偏移量为负数时表示倒数第几个
        System.out.println(valueOperations.get("append", 0L, -1L));
        System.out.println(valueOperations.get("append", -3L, -1L));
    }

    /**
     * 求某个key对应的字符串值的长度
     */
    public void size() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        valueOperations.set("append", "size");
        System.out.println(valueOperations.size("append"));
    }

    /**
     * 修改值的二进制位，即将字符串值的二进制码上的对应位置上的二进制值设为0或1
     */
    public void setBit() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        valueOperations.setBit("append", 4, false);
    }

    /**
     * 获取值的二进制位，即获取字符串值的二进制码上指定位置上的位值
     */
    public void getBit() {
        ValueOperations<String, String> valueOperations = this.stringRedisTemplate.opsForValue();
        System.out.println(valueOperations.getBit("append", 4));
    }

}
