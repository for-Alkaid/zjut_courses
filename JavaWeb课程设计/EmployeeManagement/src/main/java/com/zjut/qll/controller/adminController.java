package com.zjut.qll.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zjut.qll.mapper.DepartmentMapper;
import com.zjut.qll.mapper.EmployeeMapper;
import com.zjut.qll.mapper.PositionMapper;
import com.zjut.qll.pojo.Department;
import com.zjut.qll.pojo.Employee;
import com.zjut.qll.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class adminController {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PositionMapper positionMapper;

    @RequestMapping("/admin/index")
    public String allEmployees(Model model){
        List<Employee> employees = employeeMapper.queryEmployees();
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
        List<Department> departments = departmentMapper.queryAllDepartment();
        List<Position> positions = positionMapper.queryAllPosition();
        model.addAttribute("departments",departments);
        model.addAttribute("positions",positions);
        employees.remove(0);
        model.addAttribute("emps",employees);
        return "admin/index";
    }

    @PostMapping("/admin/updateEmployee")
    public String updateEmployee(Employee employee,Model model){
        Map<String,Object> empMap = new HashMap<>();
        empMap.put("emp_name",employee.getEmp_name());
        empMap.put("dept_id",employee.getDepartment().getId());
        empMap.put("position_id",employee.getPosition().getPosition_id());
        empMap.put("emp_id",employee.getEmp_id());

        if(employeeMapper.updateEmployee(empMap)>0){
            model.addAttribute("msg","更新成功！");
        }else{
            model.addAttribute("msg","更新失败！");
        }
        return "redirect:/admin/index";
    }
    @PostMapping("/admin/deleteEmployee")
    public String deleteEmployee(Employee employee, Model model){

        if(employeeMapper.deleteEmployee(employee)>0) {
            model.addAttribute("msg","删除成功！");
        }else{
            model.addAttribute("msg","删除失败！");
        }
        return "redirect:/admin/index";
    }
}
