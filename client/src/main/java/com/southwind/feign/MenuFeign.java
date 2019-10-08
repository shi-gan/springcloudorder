package com.southwind.feign;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;

import com.southwind.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//声明式接口调用
@FeignClient(value = "menu") //微服务在注册中心的名字,也就是服务提供者
public interface MenuFeign {

    @GetMapping("/menu/findAll/{page}/{limit}")  //微服务的controller中
    public MenuVO findAll(@PathVariable("page") int page, @PathVariable("limit") int limit);

    @DeleteMapping("/menu/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);

    @GetMapping("/menu/findTypes")
    public List<Type> findTypes();

    @PostMapping("/menu/save")
    public void save(Menu menu);

    @GetMapping("/menu/findById/{id}")
    public Menu findById(@PathVariable("id") long id);

    @PutMapping("/menu/update")
    public void update(Menu menu);
}
