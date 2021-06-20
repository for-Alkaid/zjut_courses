package com.zjut.qll.util;

import java.util.Calendar;
import java.util.Date;

public class CompareMonth {
    public static boolean compareMonth(Date date1, Date date2){
        Calendar calder = Calendar.getInstance();
        calder.setTime(date1);//设置时间
        int year1 = calder.get(Calendar.YEAR);//获取年份
        int month1 = calder.get(Calendar.MONTH);//获取月份
        calder.setTime(date2);
        int year2 = calder.get(Calendar.YEAR);//获取年份
        int month2 = calder.get(Calendar.MONTH);//获取月份
        return year1 * 100 + month1 == year2 * 100 + month2;
    }

}
