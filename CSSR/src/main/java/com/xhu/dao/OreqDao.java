package com.xhu.dao;

import com.xhu.entity.Oreq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/4/4.
 */
public interface OreqDao {
    public List<Oreq> list(@Param("index") Integer index, @Param("size") Integer size, @Param("oreq") Oreq oreq);

    public int delete(Integer lReqId);

    public Oreq getOreq(Integer lReqId);

    public int update(Oreq oreq);

    public int add(Oreq oreq);

    public int getCount(Oreq oreq);

    public int check(@Param("userId") Integer userId,@Param("orderId") String orderId);
}
