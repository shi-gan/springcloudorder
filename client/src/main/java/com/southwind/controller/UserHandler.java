package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
//import com.southwind.feign.OrderFeign;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/findAll")  //不是rest风格
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return userFeign.findAll(index, limit);
    }

    //页面跳转统一起来
//    @GetMapping("/redirect/{location}")   // user_manage
//    public String redirect(@PathVariable("location") String location){
//        return location;   // location 是 user_manage
//    }
//
//

    @GetMapping("/count")
    public int count(){
        return userFeign.count();
    }

    @PostMapping("/save") //前端传入的不是json,是传统传参不用@requestbody
    public String save(User user){
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";  // 统一在menu中跳转
    }


    @GetMapping("/deleteById/{id}")
    public String deleteId(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "redirect:/menu/redirect/user_manage";
    }

}
