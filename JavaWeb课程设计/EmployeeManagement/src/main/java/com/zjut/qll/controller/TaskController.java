package com.zjut.qll.controller;

import com.zjut.qll.mapper.TaskMapper;
import com.zjut.qll.mapper.TaskToEmpMapper;
import com.zjut.qll.pojo.Employee;
import com.zjut.qll.pojo.TaskToEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class TaskController {

//    @Autowired
//    EmployeeMapper employeeMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TaskToEmpMapper tasktoempMapper;

    private List<TaskToEmp> tasks;

    @RequestMapping("/user/workbench")
    public String toWorkbench(HttpSession session,Model model){
        Employee employee = (Employee) session.getAttribute("emp");
        tasks = tasktoempMapper.queryTaskToEmpByEmpId(employee.getEmp_id());
        model.addAttribute("tasks",tasks);
        return "user/workbench";
    }


    @RequestMapping("/user/task")
    public String tpTask(Model model){
        

        return "user/task";
    }
}
