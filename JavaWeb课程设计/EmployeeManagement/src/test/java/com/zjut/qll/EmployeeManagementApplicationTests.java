package com.zjut.qll;

import com.zjut.qll.mapper.EmployeeMapper;
import com.zjut.qll.mapper.TaskToEmpMapper;
import com.zjut.qll.pojo.Employee;
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
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() throws SQLException {

        List<Employee> employees = employeeMapper.queryEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}
