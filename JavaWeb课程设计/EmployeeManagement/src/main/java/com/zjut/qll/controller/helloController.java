package com.zjut.qll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @RequestMapping("/hi")
    @ResponseBody
    public String hi(Model model){
        return "hi";
    }


}
