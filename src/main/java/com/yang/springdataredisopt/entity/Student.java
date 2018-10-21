package com.yang.springdataredisopt.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Yang
 * @date: 2018/10/22 01:37
 * @description:
 */
@Data
@Builder
public class Student {

    private String clazz;

    private String teacher;

}
