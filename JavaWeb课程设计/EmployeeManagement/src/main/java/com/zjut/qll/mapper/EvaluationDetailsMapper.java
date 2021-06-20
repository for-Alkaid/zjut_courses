package com.zjut.qll.mapper;

import com.zjut.qll.pojo.EvaluationDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EvaluationDetailsMapper {

    List<EvaluationDetails> queryAllEvaluationDetails();

    int insertEvaluationDetails(EvaluationDetails evaluationDetails);

    EvaluationDetails queryEvaluationDetailByEvaId(@Param("eva_id")int eva_id);

    int updateEvaluationDetails(EvaluationDetails evaluationDetails);

}
