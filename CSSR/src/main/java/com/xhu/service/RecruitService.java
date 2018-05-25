package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Recruit;

/**
 * Created by gp on 2018/5/22.
 */
public interface RecruitService {
    public void add(Recruit recruit);

    public void deleteById(Integer recruitId);

    public PageBean<Recruit> list(Integer currentPage, Integer pageSize, Recruit recruit);

    public int getCount(Recruit recruit);

    public Recruit queryById(Integer recruitId);
}
