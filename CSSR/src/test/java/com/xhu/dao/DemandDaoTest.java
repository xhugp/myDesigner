package com.xhu.dao;

import com.xhu.entity.Demand;
import com.xhu.util.CommonUtil;
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
 * Created by gp on 2018/3/15.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemandDaoTest {

    @Autowired
    private DemandDao demandDao;

    @Autowired
    private CommonUtil commonUtil;

    @Test
    public void list() throws Exception {
        Demand demand1 = new Demand();
        List<Demand> list = demandDao.list(0,5,demand1);
        for (Demand demand:list) {
            System.out.print(demand);
        }
        assertEquals(5,list.size());
    }

    @Test
    @Ignore
    public void queryById() throws Exception {
    }

    @Test
    @Ignore
    public void add() throws Exception {
        Demand demand = new Demand(0,"test","asa","test",100.0f,"1",1,commonUtil.dateToString(new Date()),commonUtil.dateToString(new Date()));
        int result = demandDao.add(demand);
        assertEquals(1,result);
    }

    @Test
    @Ignore
    public void update() throws Exception {
    }

    @Test
    @Ignore
    public void deleteById() throws Exception {
    }

    @Test
    @Ignore
    public void deleteBatch() throws Exception {
    }

    @Test
    @Ignore
    public void getCount() throws Exception {
        int result = demandDao.getCount(new Demand());
        assertEquals(2,result);

    }

}