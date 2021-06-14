package com.zjut.qll.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int t_id;
    private String t_name;
//    private int p_id;
    private Project project;
}
