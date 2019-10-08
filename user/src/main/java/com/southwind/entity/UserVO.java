package com.southwind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {  //包装成laiyu可用的格式
    private int code;
    private String msg;
    private int count;
    private List<User> data;
}
