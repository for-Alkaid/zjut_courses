package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {

    List<Task> queryAllTasks();

    List<Task> queryTaskById(@Param("t_id")int id);
}
