package com.zjut.qll;

import com.zjut.qll.mapper.TaskToEmpMapper;
import com.zjut.qll.pojo.TaskToEmp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class EmployeeManagementApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    TaskToEmpMapper taskToEmpMapper;

    @Test
    void contextLoads() throws SQLException {

        List<TaskToEmp> taskToEmps = taskToEmpMapper.queryTaskToEmpByEmpId("0001");
        for (TaskToEmp taskToEmp : taskToEmps) {
            System.out.println(taskToEmp);
        }
    }

}
