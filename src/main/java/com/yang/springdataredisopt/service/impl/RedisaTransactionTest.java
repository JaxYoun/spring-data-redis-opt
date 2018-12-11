package com.yang.springdataredisopt.service.impl;

import com.yang.springdataredisopt.service.RedisTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis事务只能保证多个命令批量执行的隔离性和原子性，但是没有回滚机制
 * 开始事务、命令入队、执行事务
 */
@Service
public class RedisaTransactionTest implements RedisTransactionService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void transaction() {

        List<Boolean> txResults = stringRedisTemplate.execute(new SessionCallback<>() {
            @Override
            public List<Boolean> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();  //开始事务
                operations.opsForSet().add("key", "value1");  //第一条命令入队
                operations.expire("key", 60L, TimeUnit.SECONDS);  //第二条命令入队

                // This will contain the results of all operations in the transaction
                //执行事务，返回值是多个命令的执行结果之集合，可以从结果集取出结果以满足后续业务处理
                return operations.exec();
            }
        });

        //事务后续业务
        if (txResults.get(0) && txResults.get(1)) {
            System.out.println("都成功了");
        }
    }
}
