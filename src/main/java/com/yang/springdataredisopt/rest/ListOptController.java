package com.yang.springdataredisopt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author: Yang
 * @date: 2018/10/16 23:00
 * @description: list数据类型操作
 */
@RestController
@RequestMapping("/listOpt")
public class ListOptController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ListOperations<String, String> getOpt() {
        return this.stringRedisTemplate.opsForList();
    }

    /**
     * 添加list
     */
    public void add() {
        this.getOpt().set("myList", 0, "zero");
        this.getOpt().set("myList", 1, "one");
        this.getOpt().set("myList", 2, "two");
        this.getOpt().set("myList", 3, "two");
        this.getOpt().set("myList", 4, "two");
        this.getOpt().set("myList", 5, "two");
        this.getOpt().set("myList", 6, "two");
    }

    /**
     * 获取指定坐标区间的list元素
     */
    public void range() {
        this.getOpt().range("myList", 0, 4).forEach(System.out::println);
        this.getOpt().range("myList", 0, -1).forEach(System.out::println);
    }

    /**
     * 裁剪list，只保留指定区间内的元素
     */
    public void trim() {
        this.getOpt().trim("myList", 3, 4);
    }

    /**
     * 获取list中元素个数
     */
    public void size() {
        System.out.println(this.getOpt().size("myList"));
    }

    /**
     * 左压入，如果key还不存在就先建一个，再左压入，返回值为完成插入操作后list内元素个数
     */
    public void leftPush() {
        this.getOpt().leftPush("myList", "222");
    }

    /**
     * 左压入数组
     */
    public void leftPushAarry() {
        String[] stringarrays = new String[]{"1", "2", "3"};
        this.getOpt().leftPushAll("leftPushAll", stringarrays);
    }

    /**
     * 左压入集合
     */
    public void leftPushList() {
        this.getOpt().leftPushAll("leftPushAll", Arrays.asList("1", "2", "3", "4"));
    }

    /**
     * 向list中插入一个值，如果有重复就会返回插入后重复值的个数，如果无重复就会返回0
     */
    public void leftPushIfPresent() {
        this.getOpt().leftPushIfPresent("leftPushIfPresent", "aa");
        this.getOpt().leftPushIfPresent("leftPushIfPresent", "aa");
    }

    /**
     * 如果list中存在one这个元素，则将zero元素插入到one的左边
     */
    public void leftPushToNeiber() {
        this.getOpt().leftPush("list", "one", "zero");
    }

    /**
     * 删除list中指定值，指定值如果再list中存在重复就删除count个，count为正值则从左往右数，为负值则倒着数，为0则删除等于value的所有元素
     */
    public void remov() {
        this.getOpt().remove("list", 2, "qq");
    }

    /**
     * 获取list中指定位置的值
     */
    public void index() {
        this.getOpt().index("list", 0);
    }

    /**
     * 弹出最左边的元素，相当于出队
     */
    public void leftPop() {
        this.getOpt().leftPop("list");
    }

    /**
     * 弹出最左边的元素，相当于出队
     */
    public void leftPopWithTimeout() {
        this.getOpt().leftPop("list", 3L, TimeUnit.SECONDS);
    }


    /**
     * 将前一个list右出队，然后将这个元素从左侧入队到第二个list
     */
    public void rightPopAndLeftPush() {
        this.getOpt().rightPopAndLeftPush("list1", "list2", 5L, TimeUnit.SECONDS);
    }


}
