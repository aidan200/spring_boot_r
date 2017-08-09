package com.xianzhipt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9.
 */
@Controller
public class TestController {
    @RequestMapping("testJson")
    @ResponseBody
    public Map TestJson(){
        Map map = new HashMap();
        System.out.println(1);
        map.put("123","456");
        return map;
    }


}
