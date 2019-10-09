package com.southwind.controller;


import com.southwind.entity.User;
import com.southwind.entity.UserVO;
import com.southwind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {
//    @Value("${server.port}")
//    public String port;
//
//    @GetMapping("/index")
//    public String index(){
//        return this.port;
//    }
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
//        return new UserVO();
        return new UserVO(0,"",userRepository.count(),userRepository.findAll(index, limit));
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count(){
        return userRepository.count();
    }

    @PostMapping("/save") //微服务之间通过json传数据需要requestBody
    public void save(@RequestBody User user){
        userRepository.save(user);
    }

    @PutMapping("/update") //传入的是json数据
    public void update(@RequestBody User user){
        userRepository.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteId(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }
}
