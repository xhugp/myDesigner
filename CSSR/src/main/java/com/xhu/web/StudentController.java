package com.xhu.web;

/**
 * Created by gp on 2018/2/25.
 */

import com.xhu.dto.ResultBean;
import com.xhu.entity.Student;
import com.xhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student/list",method = RequestMethod.GET)
    private ResultBean<List> listStu(){
        List<Student> list = studentService.queryAll();
        ResultBean<List> result = new ResultBean<List>("200",list,"学生列表");
        return result;
    }

    @RequestMapping(value = "/student/{stuId}",method = RequestMethod.GET)
    private  ResultBean<Student> getStu(@PathVariable(value = "stuId",required = false) Integer stuId){
        Student student = studentService.queryById(stuId);
        ResultBean<Student> result = new ResultBean<Student>("200",student,"单个学生信息");
        return result;
    }

    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    private  Map<String,Object> addStu(Student student){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        boolean result = studentService.insertStudent(student);
        modelMap.put("success",result);
        return modelMap;
    }

    @RequestMapping(value = "/student/update",method = RequestMethod.POST)
    private  Map<String,Object> updateStu(Student student){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        boolean result = studentService.updateStudent(student);
        modelMap.put("success",result);
        return modelMap;
    }

    @RequestMapping(value = "/student/delete/{stuId}",method = RequestMethod.GET)
    private  Map<String,Object> deleteStu(@PathVariable(value = "stuId",required = false) Integer stuId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        boolean result = studentService.deleteById(stuId);
        modelMap.put("success",result);
        return modelMap;
    }
}
