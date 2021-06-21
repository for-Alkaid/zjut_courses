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
import java.util.*;

import static java.util.Collections.max;
import static java.util.Collections.min;


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

        Evaluation evaluation = evaluationMapper.queryEvaluationByEmpIdAndTime(employee.getEmp_id(), new Date());
        model.addAttribute("evaluation",evaluation);
        List<Double> yearScore = evaluationMapper.queryEvaluationByEmpId(employee.getEmp_id());
        model.addAttribute("yearScore",yearScore);
        model.addAttribute("best",max(yearScore));
        model.addAttribute("worst",min(yearScore));
        System.out.println(yearScore);
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
        String empId = evaluationDetails.getEmployee().getEmp_id();  //传过来的是被打分的id，借用一下
        Evaluation evaluation = evaluationMapper.queryEvaluationByEmpIdAndTime(empId,new Date());
        // 员工该月无评价就插入一个数据字段
        if (evaluation==null) evaluationMapper.insertEvaluation(empId,new Date());
        evaluation = evaluationMapper.queryEvaluationByEmpIdAndTime(empId,new Date());

        evaluationDetails.setEvaluation(evaluation);
        System.out.println(evaluationDetails);
        String position = (String) session.getAttribute("empPosition");
//        System.out.println(position);
        Map<String ,Object> updateMap = new HashMap<>();
        updateMap.put("position",position);
        updateMap.put("score",evaluationDetails.getScore());
        updateMap.put("emp_id",empId);
        evaluationMapper.updateSomeoneScore(updateMap);
        System.out.println(evaluationDetailsMapper.queryEvaluationDetailByEvaId(evaluation.getEva_id()));
        //给详情表中打分人的id重新赋值
        Employee emp = (Employee) session.getAttribute("emp");
        evaluationDetails.setEmployee(emp);


        if (evaluationDetailsMapper.queryEvaluationDetailByEvaId(evaluation.getEva_id())!=null){
            if (evaluationDetailsMapper.updateEvaluationDetails(evaluationDetails)>0){
                model.addAttribute("msg","更新成功");
                System.out.println("更新成功");
            }
        }
        else {
            evaluationDetailsMapper.insertEvaluationDetails(evaluationDetails);
            model.addAttribute("msg","插入成功");
            System.out.println("插入成功");
        }


        session.setAttribute("evaluations",evaluationMapper.queryAllEvaluations());

        return "redirect:/advance/workbench";
    }

}
