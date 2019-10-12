package com.southwind.controller;

import com.southwind.entity.Menu;

import com.southwind.entity.MenuVO;
import com.southwind.feign.MenuFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired  //注入接口
    private MenuFeign menuFeign;
//    @Autowired
//    private OrderFeign orderFeign;

    @GetMapping("/findAll") // 不写参数表单传入          普通的传入方式     不是restful
    @ResponseBody   // 说明只直接返回数据而不是视图 http://localhost:8030/menu/findAll?page=1&limit=10
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return menuFeign.findAll(index, limit);
    }

    //没有注解ResponseBody 返回的是视图
    @GetMapping("/redirect/{location}")   //index
    public String redirect(@PathVariable("location") String location){
        return location;   // location 是 index   或者 order
    }

    @GetMapping("/deleteById/{id}")  // 删除后请求页面
    public String deleteById(@PathVariable("id") long id){
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/menu_manage"; //menu_manage.html
    }

    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_add");
        modelAndView.addObject("list", menuFeign.findTypes());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Menu menu){
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage"; //保存后重定向,到列表中
    }

//    编辑的时候的地址
    @GetMapping("/findById/{id}") //rest风格
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");    // menu_update.html 页面   菜单编辑
        modelAndView.addObject("menu", menuFeign.findById(id)); // 被编辑的该条数据
        modelAndView.addObject("list",menuFeign.findTypes());
        return modelAndView;
    }

    @PostMapping("/update") //更新是一个post请求
    public String update(Menu menu){
        menuFeign.update(menu);  // 更新
        return "redirect:/menu/redirect/menu_manage"; //保存后重定向,到列表中
    }
}
