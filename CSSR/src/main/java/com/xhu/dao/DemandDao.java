package com.xhu.dao;

import com.xhu.dto.PageBean;
import com.xhu.entity.Demand;
import com.xhu.entity.Manage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by gp on 2018/3/11.
 */

public interface DemandDao {
    public List<Demand> list(@Param("index") Integer index,@Param("size") Integer size,@Param("demand") Demand demand);

    public Demand queryById(String demandId);

    public int add(Demand demand);

    public int update(Demand demand);

    public int deleteById(String demandId);

    public int deleteBatch(List<String> deleteList);

    public int getCount(Demand demand);

    public String getState(String demandId);
}
