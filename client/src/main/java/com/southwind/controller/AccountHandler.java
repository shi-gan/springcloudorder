package com.southwind.controller;

import com.southwind.entity.Account;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")  //  不是rest风格！！！！
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        Object object = accountFeign.login(username,password,type);//返回的是hashmap结构的
        LinkedHashMap<String, Object> hashMap = (LinkedHashMap)object;
        String result = null;
        if(object == null){
            result = "login";//密码错误重新进入登录页面
        }else{
            switch (type){
                case "user":
                    User user = new User();
                                   //integer类型 转为 string
                    String idStr = hashMap.get("id")+"";// 登陆用户的id
                    long id = Long.parseLong(idStr);
                    String nickname = (String)hashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickname);
                    session.setAttribute("user",user);
                    result = "index";    //html只能用转发的形式，需要经过后台创建session获得session
                    //handler解析视图
                    break;
                case "admin":
                    Admin admin = (Admin)object;
                    session.setAttribute("admin",admin);
                    result = "";
                    break;
            }
        }
        return result;

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();  // 使session失效
        return "redirect:/login.html";
    }

    @RequestMapping("/redirect/{target}")
    public String redirect(@PathVariable("target") String target){
        return target;
    }

//    private User convertUser(Account account){
//        User user = new User();
//        user.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
//        user.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
//        user.setGender(ReflectUtils.getFieldValue(account,"gender")+"");
//        user.setId((long)(ReflectUtils.getFieldValue(account,"id")));
//        user.setNickname(ReflectUtils.getFieldValue(account,"nickname")+"");
//        user.setRegisterdate((Date)(ReflectUtils.getFieldValue(account,"registerdate")));
//        user.setTelephone(ReflectUtils.getFieldValue(account,"telephone")+"");
//        return user;
//    }
//
//    private Admin convertAdmin(Account account){
//        Admin admin = new Admin();
//        admin.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
//        admin.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
//        admin.setId((long)(ReflectUtils.getFieldValue(account,"id")));
//        return admin;
//    }
}

