package com.hybrid.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 58 on 2017/1/20.
 */
@Controller
public class DemoController  {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
