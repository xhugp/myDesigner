package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Rreq;

/**
 * Created by gp on 2018/5/22.
 */
public interface RreqService {
    public PageBean<Rreq> list(Integer currentPage, Integer pageSize, Rreq rreq);

    public void delete(Integer rreqId);

    public Rreq getRreq(Integer rreqId);

    public void update(Rreq rreq);

    public void add(Rreq rreq);

    public int getCount(Rreq rreq);
}
