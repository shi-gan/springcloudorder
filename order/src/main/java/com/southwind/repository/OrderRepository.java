package com.southwind.repository;

import com.southwind.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);  //传入的是order
    public List<Order> findAll(int index, int limit);
                                                        // session中保存的登录用户
    public List<Order> findAllByUid(int index, int limit, int uid);//某用户的点单

    public int countByUid(int uid);

}
