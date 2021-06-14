package com.zjut.qll.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String emp_id;
    private int dept_id;
    private int position_id;
    private String emp_name;
    private String password;
}
