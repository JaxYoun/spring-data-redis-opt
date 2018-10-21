package com.yang.springdataredisopt.service;

/**
 * @author: Yang
 * @date: 2018/10/22 00:53
 * @description:
 */
public interface RedisTemplateBasicService {

    /**
     * 经过对同一类型模板的多次调用，验证其被实例化的次数和示例属性的共用行
     * 验证结果表明，
     * 1.多次Autowired注入，模板也只会实例化一次，即注入是遵循单例模式的
     * 2.由于模板的注入是单例的，所以对同一模板实例对象的属性的修改，例如序列化组件的修改会作用其他引用的地方，
     * 是有副作用的
     */
    void templateInstantCount();

}
