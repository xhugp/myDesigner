package com.xhu.dao;

import com.xhu.entity.Dtype;

import java.util.List;

/**
 * Created by gp on 2018/3/20.
 */
public interface DtypeDao {
    public String getName(Integer id);
    public int add(Dtype dtype);
    public int delete(Integer id);

    public List<Dtype> list();
}
