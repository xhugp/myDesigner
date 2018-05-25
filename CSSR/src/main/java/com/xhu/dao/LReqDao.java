package com.xhu.dao;

import com.xhu.entity.LReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/29.
 */
public interface LReqDao {
    public List<LReq> list(@Param("index") Integer index, @Param("size") Integer size, @Param("lReq") LReq lReq);

    public int delete(Integer lReqId);

    public LReq getLReq(Integer lReqId);

    public int update(LReq lReq);

    public int add(LReq lReq);

    public int getCount(LReq lReq);

    public int updateBatch(LReq lReq);

    public int check(@Param("userId") Integer userId,@Param("toolId") Integer toolId);
}
