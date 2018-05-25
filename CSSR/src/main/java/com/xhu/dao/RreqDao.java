package com.xhu.dao;

import com.xhu.entity.Rreq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/5/22.
 */
public interface RreqDao {
    public List<Rreq> list(@Param("index") Integer index, @Param("size") Integer size, @Param("rreq") Rreq rreq);

    public int delete(Integer rreqId);

    public Rreq getRreq(Integer rreqId);

    public int update(Rreq rreq);

    public int add(Rreq rreq);

    public int getCount(Rreq rreq);

    public int check(Rreq rreq);
}
