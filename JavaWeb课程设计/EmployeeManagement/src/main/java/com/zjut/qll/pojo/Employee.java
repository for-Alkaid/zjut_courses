package com.zjut.qll.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String emp_id;
//    private int dept_id;
//    private int position_id;
    private Position position;
    private Department department;
    private String emp_name;
    private String password;
    private int clock_in;
}
