package com.zjut.qll.mapper;

import com.zjut.qll.pojo.TaskToEmp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskToEmpMapper {

    List<TaskToEmp> queryAllTaskToEmp();

    //通过员工id查询员工任务
    List<TaskToEmp> queryTaskToEmpByEmpId(@Param("emp_id") String id);

    int insertTaskToEmp(Map map);

}
