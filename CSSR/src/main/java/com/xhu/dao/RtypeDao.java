package com.xhu.dao;

import com.xhu.entity.Rtype;

import java.util.List;

/**
 * Created by gp on 2018/5/22.
 */
public interface RtypeDao {
    public int add(Rtype rtype);
    public int delete(Integer id);
    public String getName(Integer id);

    public List<Rtype> list();
}
