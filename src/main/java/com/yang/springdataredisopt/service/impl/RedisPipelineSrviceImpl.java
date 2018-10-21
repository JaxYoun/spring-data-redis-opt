package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.entity.User;
import com.yang.springdataredisopt.service.RedisPipelineSrvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yang
 * @date: 2018/10/21 23:10
 * @description:
 */
@Slf4j
@Service
public class RedisPipelineSrviceImpl implements RedisPipelineSrvice {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void redisPipeline() {
        List list = this.redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (int i = 0; i < 1000; i++) {
                    String key = "myKey" + i;
                    connection.set(key.getBytes(), key.getBytes());
                }
                return null;
            }
        }, this.redisTemplate.getValueSerializer());
        System.out.println(list.size());
    }

    @Override
    public void redisPipelineVaExecute() {
        this.stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                connection.keys("myKey11".getBytes());
                List<Object> objects = connection.closePipeline();
                System.out.println(objects.size());
                return null;
            }
        });
    }
}
