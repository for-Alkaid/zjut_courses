package com.zjut.qll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RouterController {

//    @RequestMapping("/user/index")
//    public String backIndex(){
//        //TODO
//        System.out.println("-");
//        return "index";
//    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

}
