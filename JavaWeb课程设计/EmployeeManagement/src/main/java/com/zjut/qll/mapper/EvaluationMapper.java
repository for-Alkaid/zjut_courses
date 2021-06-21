package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Evaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EvaluationMapper {

    List<Evaluation> queryAllEvaluations();


    //默认查询该人该月的评价（查询可整合为一个动态sql）
    Evaluation queryEvaluationByEmpIdAndTime(@Param("emp_id")String emp_id,@Param("eva_time")Date eva_time);


    int insertEvaluation(@Param("emp_id") String  emp_id,@Param("eva_time") Date eva_time);

    int updateSomeoneScore(Map map);

    // 若无第二个参数需要传null
    // 查询所有评价记录
    List<Evaluation> queryEvaluationByEmpId(@Param("emp_id")String emp_id);
    // 查询一年评价记录
    List<Evaluation> queryEvaluationByEmpId(@Param("emp_id")String emp_id,@Param("eva_time")Date eva_time);

//    List<Evaluation> queryEvaluationByEmpIdCurrentYear(@Param("emp_id")String emp_id);



}
