package com.southwind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//将Menu包装成MenuVO格式
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {    //将 List<Menu>包装一下，返回json格式的数据适配laiyu
    private int code;
    private String msg;
    private int count;
    private List<Menu> data;
}
