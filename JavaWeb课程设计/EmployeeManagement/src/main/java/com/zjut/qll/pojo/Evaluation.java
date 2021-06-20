package com.zjut.qll.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    private int eva_id;
    private Employee employee;
    private int departmentManagerScore;
    private int technicalDirectorScore;
    private int generalManagerScore;
    private int monthScore;
    private Date eva_time;
}
