package com.yang.springdataredisopt.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Yang
 * @date: 2018/10/21 23:56
 * @description:
 */
@Data
@Builder
public class User implements Serializable {

    private Integer id;

    private String name;

    private List<Student> studentList;

    private List<LocalDateTime> timeList;

}
