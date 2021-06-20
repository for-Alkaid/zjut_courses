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

    Evaluation queryEvaluationByEmpIdAndTime(@Param("emp_id")String emp_id,@Param("eva_time")Date eva_time);

    int insertEvaluation(@Param("emp_id") String  emp_id,@Param("eva_time") Date eva_time);

    int updateSomeoneScore(Map map);

    //查找最新更新的评价id
    int queryLastEvaId();

}
