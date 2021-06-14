package com.zjut.qll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping("/index")
    public String backIndex(){
        //TODO
        System.out.println("-");
        return "index";
    }





}
