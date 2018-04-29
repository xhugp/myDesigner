package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Message;

/**
 * Created by gp on 2018/3/22.
 */
public interface MessageService {
    public PageBean<Message> list(Integer currentPage,Integer pageSize,Message message);

    public Message getMes(String mesId);

    public void add(Message message);

    public void update(Message message);

    public void delete(String mesId);

    public int getCount(Message message);
}
