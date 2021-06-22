package com.zjut.qll.controller;

import com.zjut.qll.mapper.ProjectMapper;
import com.zjut.qll.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectMapper projectMapper;

    @RequestMapping("/advance/project")
    public String toProject(Model model){
        List<Project> projects = projectMapper.queryAllProjects();
        model.addAttribute("projects",projects);
        return "advance/project";
    }

    @RequestMapping("/advance/updateProject")
    private String updateProject(Project project){
        projectMapper.updateProject(project);
        return "redirect:/advance/project";
    }


    // 连带任务应该也被删除
    @RequestMapping("/advance/deleteProject")
    private String deleteProject(Project project){
        projectMapper.deleteProject(project);
        return "redirect:/advance/project";
    }

    @RequestMapping("/advance/addProject")
    private String addProject(Project project){
        projectMapper.insertProject(project);
        return "redirect:/advance/project";
    }
}
