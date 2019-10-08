package com.southwind.controller;


import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.entity.Type;
import com.southwind.repository.MenuRepository;
import com.southwind.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //服务提供者配置文件在配置中心
@RequestMapping("/menu")  // 访问的是配置中心的端口 8020
public class MenuHandler {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return "order的端口："+this.port;
    }

    @Autowired
    private MenuRepository menuRepository;  //接口
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        // 将List<Menu>封装进MenuVO         //menu总条数
        return new MenuVO(0,"",menuRepository.count(),menuRepository.findAll(index, limit));
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeRepository.findAll();
    }

    @PostMapping("/save")  //传入的是json数据
    public void save(@RequestBody Menu menu){
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        return menuRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menuRepository.update(menu);
    }

}