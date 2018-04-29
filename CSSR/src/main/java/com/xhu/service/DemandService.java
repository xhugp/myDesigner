package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Demand;

import java.util.List;

/**
 * Created by gp on 2018/3/15.
 */
public interface DemandService {

    public PageBean<Demand> list(Integer currentPage,Integer pageSize,Demand demand);

    public Demand queryById(String demandId);

    public void add(Demand manage);

    public void update(Demand manage);

    public void deleteById(String demandId);

    public void deleteBatch(String deleteIds);

    public int getCount(Demand demand);

    public void reUpdate(Demand demand);

}
