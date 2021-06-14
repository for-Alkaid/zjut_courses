package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface EmployeeMapper {

    Employee queryEmployeeByName(@Param("emp_name") String name);

    Employee queryEmployeeById(@Param("emp_id") String id);

    List<Employee> queryEmployees();

    int insertEmployee(Employee employee);
}
