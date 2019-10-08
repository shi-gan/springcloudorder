package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
//import com.southwind.feign.OrderFeign;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return userFeign.findAll(index, limit);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userFeign.findById(id);
    }

    @GetMapping("/count")
    public int count(){
        return userFeign.count();
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userFeign.save(user);
    }

    @PutMapping("/update") //传入的是json数据
    public void update(@RequestBody User user){
        userFeign.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteId(@PathVariable("id") long id){
        userFeign.deleteById(id);
    }

}
