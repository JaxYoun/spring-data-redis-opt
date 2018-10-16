package com.yang.springdataredisopt.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author: Yang
 * @date: 2018/10/16 22:11
 * @description: 只配置了用于Redis服务端交互的模板配置
 */
@Configuration
public class RedisConfig {

    /**
     * 这是一个比较标准的Redis模板配置样例，供参考
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> RedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //1.实例化序列化组件，此处用jackson
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        //2.实例化Jackson的json映射器并配属性
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        //3.将json映射器配置给序列化组件
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //4.实例化Redis模板，并为之配置连接工厂
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //5.给模板配置序列化组建
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * String类型模板实例化处，这里使用Spring-data-redis内置提供的模板类
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

}