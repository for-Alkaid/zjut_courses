package com.zjut.qll.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDetails {

//    private int eva_id;
    private  Evaluation evaluation;
//    private int position_id;
    private Employee employee;
    private int progressRate;
    private int quality;
    private int workload;
    private int customerReviews;
    private int responsibility;
    private int attendance;
    private int positivity;
    private int conduct;
    private int teamCooperation;
    private int improvingCapability;
    private int examSituation;
    private int specialContribution;
    private int reasonableSuggestion;
    private double Score;


    public void calculate(){
        Score += 0.15*(progressRate+quality)
                  +0.1*(workload+customerReviews+responsibility+attendance+positivity)
                  +0.05*(conduct+teamCooperation+improvingCapability+examSituation)
                  +specialContribution+reasonableSuggestion;
    }
}
