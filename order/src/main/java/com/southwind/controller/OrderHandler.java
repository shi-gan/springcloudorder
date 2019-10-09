package com.southwind.controller;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController     //服务提供者配置文件在配置中心
@RequestMapping("/order")  // 访问的是配置中心的端口 8010
public class OrderHandler {

//    @Value("${server.port}")
//    private String port;
//
//    @GetMapping("/index")
//    public String index(){
//        return "order的端口："+this.port;
//    }
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/findAllByUid/{index}/{limit}/{uid}")  //其中的uid是session中得到的
    public OrderVO findAllByUid(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") long uid){

        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUid(uid));
        orderVO.setData(orderRepository.findAllByUid(index, limit, uid));
        return orderVO;
    }

    @GetMapping("/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid){
        return orderRepository.countByUid(uid);
    }

}