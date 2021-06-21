package com.zjut.qll;

import com.zjut.qll.mapper.EmployeeMapper;
import com.zjut.qll.mapper.EvaluationMapper;
import com.zjut.qll.mapper.TaskMapper;
import com.zjut.qll.mapper.TaskToEmpMapper;
import com.zjut.qll.pojo.Employee;
import com.zjut.qll.pojo.Evaluation;
import com.zjut.qll.pojo.Task;
import com.zjut.qll.pojo.TaskToEmp;
import com.zjut.qll.util.CompareMonth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;



@SpringBootTest
class EmployeeManagementApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    TaskToEmpMapper taskToEmpMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    EvaluationMapper evaluationMapper;

    @Test
    void contextLoads() throws SQLException {
//        List<Evaluation> evaluations = evaluationMapper.queryAllEvaluations();
//        for (Evaluation evaluation : evaluations) {
//            System.out.println(evaluation);
//        }


        List<Evaluation> e = evaluationMapper.queryEvaluationByEmpId("0001",null);
        System.out.println(e);
//        for (Evaluation evaluation : list) {
//            System.out.println(evaluation);
//        }
    }



}
