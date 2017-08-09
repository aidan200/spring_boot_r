package com.xianzhipt.controller;

import com.xianzhipt.bean.User;
import com.xianzhipt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public Map show(@RequestParam(value = "name")String name,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "1") Integer rows){
        Page<User> users = userService.findUserByName(page,rows,name);
        Map map = new HashMap();
        map.put("users",users.getContent());
        map.put("total", users.getTotalElements());
        map.put("pages", users.getTotalPages());
        map.put("page", users.getNumber());
        return map;
    }

    @RequestMapping(value = "/showAll")
    @ResponseBody
    public Map showAll(@RequestParam(value = "name")String name,String pwd,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "1") Integer rows){
        Map smap = new HashMap();
        smap.put("name",name);
        smap.put("pwd",pwd);
        Page<User> users = userService.findUserAll(page,rows,smap);
        Map map = new HashMap();
        map.put("users",users.getContent());
        map.put("total", users.getTotalElements());
        map.put("pages", users.getTotalPages());
        map.put("page", users.getNumber());
        return map;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Map delete(Long id){
        Map map = new HashMap();
        userService.deleteUserById(id);
        return map;
    }
}
