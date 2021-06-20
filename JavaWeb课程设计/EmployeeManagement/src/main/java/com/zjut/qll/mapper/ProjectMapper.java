package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {

    List<Project> queryAllProjects();

    int insertProject(Project project);
}
