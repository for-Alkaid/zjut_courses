package com.zjut.qll.mapper;

import com.zjut.qll.pojo.TaskToEmp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskToEmpMapper {

    List<TaskToEmp> queryAllTaskToEmp();

    //吐过员工id查询员工任务
    List<TaskToEmp> queryTaskToEmpByEmpId(@Param("emp_id") String id);

}
