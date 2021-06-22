package com.zjut.qll.controller;

import com.zjut.qll.mapper.*;
import com.zjut.qll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.Month;
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

        Evaluation currentMonthEvaluation = evaluationMapper.queryEvaluationByEmpIdAndTime(employee.getEmp_id(), new Date());
        String assess;  // 评价等级判断
        if (currentMonthEvaluation == null){
            currentMonthEvaluation = new Evaluation(0,employee,0,0,0,0,new Date());
            assess = "-";
        }
        else {
            if (currentMonthEvaluation.getMonthScore() >= 95) assess = "A";
            else if (currentMonthEvaluation.getMonthScore() >= 85) assess = "A";
            else if (currentMonthEvaluation.getMonthScore() >= 75) assess = "B";
            else if (currentMonthEvaluation.getMonthScore() >= 60) assess = "C";
            else assess = "D";
        }
        model.addAttribute("evaluation",currentMonthEvaluation);
        model.addAttribute("assess",assess);

        // 查出所有个人今年的所有评价
        List<Evaluation> currentYearEvaluation = evaluationMapper.queryEvaluationByEmpId(employee.getEmp_id(),null);
        for (Evaluation evaluation : currentYearEvaluation) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(evaluation.getEva_time());
            System.out.println(calendar.get(Calendar.MONTH));
            System.out.println(evaluation.getMonthScore());
        }

        //构造一整年的评价分数
        Double[] yearScore = new Double[12];
        for (int i = 0; i < yearScore.length; i++) {
            yearScore[i] = 0.0;
        }
        for (Evaluation evaluation : currentYearEvaluation) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(evaluation.getEva_time());
            yearScore[calendar.get(Calendar.MONTH)] = evaluation.getMonthScore();
        }

        for (Double aDouble : yearScore) {
            System.out.println(aDouble);
        }

        model.addAttribute("yearScore",yearScore);
        model.addAttribute("best",Collections.max(Arrays.asList(yearScore)));
        model.addAttribute("worst",Collections.min(Arrays.asList(yearScore)));
        //季度分数
        double[] quarterlyScore = new double[12];
        for (int i = 0; i < yearScore.length; i++) {
            if((i+1)%3==0) {
                quarterlyScore[i] = (yearScore[i-1]+yearScore[i-2]+yearScore[i])/3;
                quarterlyScore[i-2] = quarterlyScore[i-1] = quarterlyScore[i] ;
            }
        }
        model.addAttribute("quarterlyScore",quarterlyScore);
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


    @RequestMapping("/user/accomplish")
    public String accomplish(TaskToEmp taskToEmp){
        if (taskToEmpMapper.updateAccomplish(taskToEmp)>0){
            System.out.println("更新成功！");
        }
        System.out.println(taskToEmp);
        return "redirect:/user/task";
    }

}
