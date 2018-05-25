package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Sreq;

/**
 * Created by gp on 2018/4/23.
 */
public interface SreqService {
    public PageBean<Sreq> list(Integer currentPage, Integer pageSize, Sreq sreq);

    public void delete(Integer srId);

    public Sreq getSreq(Integer srId);

    public void update(Sreq sreq);

    public void add(Sreq sreq);

    public int getCount(Sreq sreq);
}
