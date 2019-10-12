package com.southwind.entity;

import lombok.Data;

import java.util.Date;

@Data // 点单包含用户，菜单....
public class Order {
    private long id;
    private User user;   // 订单 用户
    private Menu menu;
    private Admin admin; // 订单 管理员
    private Date date;
    private int state;
}
