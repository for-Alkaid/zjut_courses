package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskMapper {

    List<Task> queryAllTasks();

    Task queryTaskById(@Param("t_id")int id);

    int queryLastTaskId();

    int insertTask(Map taskMap);


}
