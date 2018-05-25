package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Lease;

/**
 * Created by gp on 2018/3/22.
 */
public interface LeaseService {
    public PageBean<Lease> list(Integer currentPage, Integer pageSize, Lease lease);

    public Lease getLease(String leaseId);

    public Lease getLeaseByTool(Integer toolId);

    public void add(Lease lease);

    public void update(Lease lease,boolean isOver);

    public void updateOver(Lease lease);

    public void delete(String leaseId);

    public int getCount(Lease lease);
}
