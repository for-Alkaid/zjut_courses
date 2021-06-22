package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {

    List<Project> queryAllProjects();

    int insertProject(@Param("project") Project project);

    int updateProject(@Param("project") Project project);

    int deleteProject(@Param("project") Project project);
}
