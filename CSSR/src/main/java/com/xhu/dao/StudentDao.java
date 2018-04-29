package com.xhu.dao;

import com.xhu.entity.Student;

import java.util.List;

/**
 * Created by gp on 2018/2/25.
 */
public interface StudentDao {
    public List<Student> queryAll();

    public Student queryById(Integer stuId);

    public int insertStudent(Student student);

    public int updateStudent(Student student);

    public int deleteById(Integer stuId);
}
