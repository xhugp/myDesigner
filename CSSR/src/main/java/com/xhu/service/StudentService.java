package com.xhu.service;

import com.xhu.entity.Student;

import java.util.List;

/**
 * Created by gp on 2018/2/25.
 */
public interface StudentService {
    public List<Student> queryAll();

    public Student queryById(int stuId);

    public boolean insertStudent(Student student);

    public boolean updateStudent(Student student);

    public boolean deleteById(int stuId);
}
