package com.zjut.qll.controller;

import com.zjut.qll.pojo.Task;
import com.zjut.qll.pojo.TaskToEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

public class SearchController {

    @Autowired


    @RequestMapping("/user/search/project")
    public String searchProject(TaskToEmp tasks,HttpSession session){


        session.setAttribute("tasks",tasks);

        return "redirect:/user/workbench";
    }
}
