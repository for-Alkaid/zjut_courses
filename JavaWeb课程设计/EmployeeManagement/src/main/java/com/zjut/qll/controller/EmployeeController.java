package com.zjut.qll.controller;

import com.zjut.qll.mapper.DepartmentMapper;
import com.zjut.qll.mapper.EmployeeMapper;
import com.zjut.qll.mapper.PositionMapper;
import com.zjut.qll.pojo.Department;
import com.zjut.qll.pojo.Employee;
import com.zjut.qll.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PositionMapper positionMapper;

    @RequestMapping("/user/login")
    public String login(@RequestParam("emp_id")String id,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) throws NoSuchAlgorithmException {
        Employee employee = employeeMapper.queryEmployeeById(id);
        if(employee == null ){
            model.addAttribute("msg","用户名不存在！");
            return "login";
        }

        //将密码用MD5验证
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!StringUtils.isEmpty(password) && password.equals(employee.getPassword())) {
            session.setAttribute("emp",employee);
            if(id.equals("0000")){
                return "redirect:/admin/index";
            }
//            model.addAttribute("empName",employee.getEmp_name());
            String positionName = employee.getPosition().getPosition_name();
            session.setAttribute("empPosition",positionName);

            if(positionName.equals("common")) return "redirect:/user/index";
            else return "redirect:/advance/index";
        } else{
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }
    }

    @RequestMapping("/user/toRegister")
    public String toRegister(Model model){
        List<Department> departments = departmentMapper.queryAllDepartment();
        List<Position> positions = positionMapper.queryAllPosition();
        model.addAttribute("departments",departments);
        model.addAttribute("positions",positions);
        return "register";
    }

    @RequestMapping("/user/toLogin")
    public String tologin(){
        return "redirect:/login";
    }


    @RequestMapping("/user/register")
    public String register(Employee employee,Model model){
        List<Employee> e = employeeMapper.queryEmployees();
        for (Employee emp : e) {
            if(emp.getEmp_id().equals(employee.getEmp_id())){
                model.addAttribute("msg","员工ID已存在！");
                return "register";
            }
        }
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        if(employeeMapper.insertEmployee(employee)>0){
            model.addAttribute("msg","注册成功！");
        }
        return "register";
    }

}
