package com.zjut.qll.mapper;

import com.zjut.qll.pojo.EvaluationDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EvaluationDetailsMapper {

    List<EvaluationDetails> queryAllEvalutionDetails();

    int insertEvalutionDetails(EvaluationDetails evaluationDetails);



}
