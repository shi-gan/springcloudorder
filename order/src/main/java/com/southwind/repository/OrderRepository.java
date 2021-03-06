package com.southwind.repository;

import com.southwind.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);  //传入的是order
    public List<Order> findAll(int index, int limit);
                                                        // session中保存的登录用户
    public List<Order> findAllByUid(int index, int limit, long uid);//某用户的点单

    public int countByUid(long uid);
//    public List<Order> findAll(int index, int limit);
    public void updateState(long id); //通过id修改订单
    public int count();
}
