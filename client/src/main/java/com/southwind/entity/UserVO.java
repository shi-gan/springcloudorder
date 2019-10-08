package com.southwind.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {  //包装成laiyu可用的格式
    private int code;
    private String msg;
    private int count;
    private List<User> data;
}
