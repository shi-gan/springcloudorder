package com.southwind.entity;

import lombok.Data;

import java.util.Date;

@Data // 点单包含用户，菜单....
public class Order {
    private long id;
    private User user;
    private Menu menu;
    private Admin admin;
    private Date date;
    private int state;
}
