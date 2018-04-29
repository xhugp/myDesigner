package com.xhu.dao;

import com.xhu.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/21.
 */
public interface MessageDao {
    public List<Message> list(@Param("index") Integer index, @Param("size") Integer size,@Param("mes") Message message);

    public Message getMes(String mesId);

    public int add(Message message);

    public int update(Message message);

    public int delete(String mesId);

    public int getCount(Message message);
}
