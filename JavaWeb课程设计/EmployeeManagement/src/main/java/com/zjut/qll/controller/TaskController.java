package com.zjut.qll.controller;

import com.zjut.qll.mapper.*;
import com.zjut.qll.pojo.*;
import com.zjut.qll.util.CompareMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TaskController {

//    @Autowired
//    EmployeeMapper employeeMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TaskToEmpMapper taskToEmpMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EvaluationDetailsMapper evaluationDetailsMapper;
    @Autowired
    EvaluationMapper evaluationMapper;


    private List<TaskToEmp> tasks;

    @RequestMapping("/user/workbench")
    public String toWorkbench(HttpSession session,Model model){
        Employee employee = (Employee) session.getAttribute("emp");
        tasks = taskToEmpMapper.queryTaskToEmpByEmpId(employee.getEmp_id());
        session.setAttribute("tasks",tasks);
//        model.addAttribute("tasks",tasks);
        if (employee.getPosition().getPosition_id()>1) {
            return "redirect:/advance/workbench";
        }
        return "user/workbench";
    }

    @RequestMapping("/user/task")
    public String toTask(HttpSession session,Model model){
        Employee employee = (Employee) session.getAttribute("emp");
        tasks = taskToEmpMapper.queryTaskToEmpByEmpId(employee.getEmp_id());
        session.setAttribute("tasks",tasks);

        return "user/task";
    }

    @RequestMapping("/advance/addTask")
    public String addTask(HttpSession session, Model model, TaskToEmp taskToEmp, Task task){
        // 用map整合传入的task表的字段的变量
        Map<String,Object> taskMap = new HashMap<>();
        taskMap.put("t_name",task.getT_name());
        taskMap.put("p_id",task.getProject().getP_id());
        taskMapper.insertTask(taskMap);

        // 用map整合传入的tasktoemp表的字段的变量
        Map<String,Object> tasktoempMap = new HashMap<>();
        tasktoempMap.put("emp_id",taskToEmp.getEmployee().getEmp_id());
        tasktoempMap.put("task_id",taskMapper.queryLastTaskId());
        tasktoempMap.put("startTime",taskToEmp.getStartTime());
        tasktoempMap.put("endTime",taskToEmp.getEndTime());
        tasktoempMap.put("details",taskToEmp.getDetails());
        taskToEmpMapper.insertTaskToEmp(tasktoempMap);

        session.setAttribute("taskToEmp",taskToEmpMapper.queryAllTaskToEmp());

        return "redirect:/advance/workbench";
    }

    @RequestMapping("/advance/evaluate")
    public String evaluate(HttpSession session,EvaluationDetails evaluationDetails, Model model){

        // 加权计算某一上级的总分
        evaluationDetails.calculate();
        System.out.println(evaluationDetails.getScore());
        String empId = evaluationDetails.getEmployee().getEmp_id();
        Evaluation evaluation = evaluationMapper.queryEvaluationByEmpIdAndTime(empId,new Date());
        // 员工该月无评价就插入一个数据字段
        if (evaluation==null) evaluationMapper.insertEvaluation(empId,new Date());
        evaluation = evaluationMapper.queryEvaluationByEmpIdAndTime(empId,new Date());

        evaluationDetails.setEvaluation(evaluation);


        if (evaluationDetailsMapper.insertEvalutionDetails(evaluationDetails)>0){
            // 通过Map更新
            Map<String ,Object> updateMap = new HashMap<>();
            updateMap.put("position",session.getAttribute("empPosition"));
            updateMap.put("score",evaluationDetails.getScore());
            updateMap.put("emp_id",evaluationDetails.getEmployee().getEmp_id());
            evaluationMapper.updateSomeoneScore(updateMap);
            model.addAttribute("msg","插入成功");
        }
        session.setAttribute("evaluations",evaluationMapper.queryAllEvaluations());

        return "redirect:/advance/workbench";
    }

}
