package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface EmployeeMapper {

    Employee queryEmployeeByName(@Param("emp_name") String name);

    Employee queryEmployeeById(@Param("emp_id") String id);

    List<Employee> queryEmployees();

    int insertEmployee(@Param("employee") Employee employee);

    int updateEmployee(Map<String,Object> employee);

    int deleteEmployee(Employee employee);
}
