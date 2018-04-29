package com.xhu.service.impl;

import com.xhu.dao.StudentDao;
import com.xhu.entity.Student;
import com.xhu.exception.StudentException;
import com.xhu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/2/25.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> queryAll() {
        return studentDao.queryAll();
    }

    @Override
    public Student queryById(int stuId) {
       return studentDao.queryById(stuId);
    }

    @Transactional
    @Override
    public boolean insertStudent(Student student) {
        if(null!=student.getStuName() && !"".equals(student.getStuName())){
            student.setCreateTime(new Date());
            student.setLastUpdateTime(new Date());
            try {
                int result = studentDao.insertStudent(student);
                if(result > 0){
                    return true;
                }else{
                    throw new StudentException("插入学生信息失败！");
                }
            }catch (Exception e){
                throw new StudentException(e.getMessage());
            }
        }else{
            throw new StudentException("学生姓名不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean updateStudent(Student student) {
        if(student.getStuId() > 0){
            student.setLastUpdateTime(new Date());
            try {
                int result = studentDao.updateStudent(student);
                if(result > 0){
                    return true;
                }else{
                    throw new StudentException("更新学生信息失败！");
                }
            }catch (Exception e){
                throw new StudentException(e.getMessage());
            }
        }else{
            throw new StudentException("学生Id不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteById(int stuId) {
        if (stuId > 0) {
            try {
                int result = studentDao.deleteById(stuId);
                if (result > 0) {
                    return true;
                } else {
                    throw new StudentException("未找到该学生");
                }
            } catch (Exception e) {
                throw new StudentException(e.getMessage());
            }
        } else {
            throw new StudentException("学生Id格式不正确！");
        }
    }
}
