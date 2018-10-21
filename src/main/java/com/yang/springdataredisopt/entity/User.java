package com.yang.springdataredisopt.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Yang
 * @date: 2018/10/21 23:56
 * @description:
 */
@Data
@Builder
public class User {

    private Integer id;

    private String name;

}
