package com.xhu.dao;

import com.xhu.entity.Ttype;

import java.util.List;

/**
 * Created by gp on 2018/3/21.
 */
public interface TtypeDao {
    public int add(Ttype dtype);
    public int delete(Integer id);

    public List<Ttype> list();
}
