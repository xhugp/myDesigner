package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Oreq;

/**
 * Created by gp on 2018/4/4.
 */
public interface OreqService {
    public PageBean<Oreq> list(Integer currentPage, Integer pageSize, Oreq oreq);

    public void delete(Integer oreqId);

    public Oreq getOreq(Integer oreqId);

    public void updateResult(Oreq oreq,boolean isOver);

    public void add(Oreq oreq);

    public int getCount(Oreq oreq);
}
