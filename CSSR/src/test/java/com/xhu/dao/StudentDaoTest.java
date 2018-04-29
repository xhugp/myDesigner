package com.xhu.dao;

import com.xhu.CssrApplication;
import com.xhu.entity.Student;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gp on 2018/2/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {
    @Autowired
    private StudentDao studentDao;
    @Test
    @Ignore
    public void queryAll() throws Exception {
        List<Student> list = studentDao.queryAll();
        assertEquals(1,list.size());
    }

    @Test
    @Ignore
    public void queryById() throws Exception {
        Student student = studentDao.queryById(1);
        assertEquals("张三",student.getStuName());
    }

    @Test
    public void insertStudent() throws Exception {
        Student student = new Student();
        student.setStuName("张三");
        student.setPxh(1);
        int result = studentDao.insertStudent(student);
        assertEquals(1,result);
    }

    @Test
    @Ignore
    public void updateStudent() throws Exception {
        Student student = new Student();
        student.setStuId(2);
        student.setStuName("王五");
        student.setPxh(1);
        student.setLastUpdateTime(new Date());
        int result = studentDao.updateStudent(student);
        assertEquals(1,result);
    }

    @Test
    @Ignore
    public void deleteById() throws Exception {
        int result = studentDao.deleteById(1);
        assertEquals(1,result);
    }

}