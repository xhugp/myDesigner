package com.xhu.dao;

import com.xhu.entity.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/28.
 */
public interface SignDao {
    public List<Sign> list(@Param("index") Integer index, @Param("size") Integer size, @Param("sign") Sign sign);

    public Sign getSign(Sign sign);

    public int delete(Sign sign);

    public int add(Sign sign);

    public int getCount(Sign sign);

}
