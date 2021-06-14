package com.zjut.qll.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskToEmp {
    private int te_id;
//    private String emp_id;
//    private int task_id;
    private Employee employee;
    private Task task;
    private Date startTime;
    private Date endTime;
    private int isAccomplish;
}
