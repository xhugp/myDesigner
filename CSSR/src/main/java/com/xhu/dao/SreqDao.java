package com.xhu.dao;

import com.xhu.entity.Sreq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/4/23.
 */
public interface SreqDao {
    public List<Sreq> list(@Param("index") Integer index, @Param("size") Integer size, @Param("sreq") Sreq sreq);

    public int delete(Integer srId);

    public Sreq getSreq(Integer srId);

    public int update(Sreq sreq);

    public int add(Sreq sreq);

    public int getCount(Sreq sreq);

    public int check(Sreq sreq);

}
