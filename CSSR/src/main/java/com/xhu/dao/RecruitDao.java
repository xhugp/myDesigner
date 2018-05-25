package com.xhu.dao;

import com.xhu.entity.Recruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/5/22.
 */
public interface RecruitDao {
    public List<Recruit> list(@Param("index") Integer index, @Param("size") Integer size, @Param("recruit") Recruit recruit);

    public Recruit queryById(Integer recruitId);

    public int add(Recruit recruit);

    public int deleteById(Integer recruitId);

    public int getCount(Recruit recruit);

}
