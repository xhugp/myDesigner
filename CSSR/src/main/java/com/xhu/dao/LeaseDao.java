package com.xhu.dao;

import com.xhu.entity.Lease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/21.
 */
public interface LeaseDao {
    public List<Lease> list(@Param("index") Integer index,@Param("size") Integer size,@Param("lease") Lease lease);

    public Lease getLease(String leaseId);

    public Lease getLeaseByTool(Lease lease);

    public Lease getLeaseByUser(@Param("toolId") Integer toolId,@Param("userId") Integer userId);

    public int add(Lease lease);

    public int update(Lease lease);

    public int delete(String leaseId);

    public int getCount(Lease lease);
}
