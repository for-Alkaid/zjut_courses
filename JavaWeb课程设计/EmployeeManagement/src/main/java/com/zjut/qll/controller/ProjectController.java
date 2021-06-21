package com.zjut.qll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

    @RequestMapping("/advance/project")
    public String toProject(){

        return "advance/project";
    }
}
