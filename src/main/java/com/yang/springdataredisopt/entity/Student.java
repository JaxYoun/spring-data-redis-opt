package com.yang.springdataredisopt.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author: Yang
 * @date: 2018/10/22 01:37
 * @description:
 */
@Data
@NoArgsConstructor
public class Student implements Serializable {

    private String clazz;

    private String teacher;

}
