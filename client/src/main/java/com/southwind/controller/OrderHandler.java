package com.southwind.controller;

import com.southwind.entity.*;
import com.southwind.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/save/{mid}")   //点单操作，保存该菜单进入order  user的id
    public String save(@PathVariable("mid") long mid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        Menu menu = new Menu();
        menu.setId(mid);  // t_order数据库中只用到 menu的id
        order.setUser(user);
        order.setMenu(menu);// order类中包含了其他的类 （Menu, User.....）
        order.setDate(new Date());
        orderFeign.save(order);
        return "index";
    }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        User user = (User) session.getAttribute("user");
        int index = (page-1)*limit;
        return orderFeign.findAllByUid(index, limit, user.getId());//uid从session中取出
    }
//
//    @GetMapping("/findAllByState")
//    @ResponseBody
//    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit){
//        return orderFeign.findAllByState(0, page, limit);
//    }
//
//    @GetMapping("/updateState/{id}/{state}")
//    public String updateState(@PathVariable("id") long id,@PathVariable("state") int state,HttpSession session){
//        Admin admin = (Admin) session.getAttribute("admin");
//        orderFeign.updateState(id,state,admin.getId());
//        return "redirect:/account/redirect/order_handler";
//    }
}
