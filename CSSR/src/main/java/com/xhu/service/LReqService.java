package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.LReq;

import java.util.List;

/**
 * Created by gp on 2018/3/29.
 */
public interface LReqService {
    public PageBean<LReq> list(Integer currentPage,Integer pageSize,LReq lReq);

    public void delete(Integer lReqId);

    public LReq getLReq(Integer lReqId);

    public void updateResult(LReq lReq,boolean isOver);

    public void add(LReq lReq);

    public int getCount(LReq lReq);
}
