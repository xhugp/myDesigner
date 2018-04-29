package com.xhu.service.impl;

import com.xhu.dao.MessageDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Message;
import com.xhu.exception.MessageException;
import com.xhu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gp on 2018/3/22.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public PageBean<Message> list(Integer currentPage, Integer pageSize, Message message) {
        int count = getCount(message);
        try {
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List list = messageDao.list(pageBean.getIndex(),pageBean.getSize(),message);
            pageBean.setDatas(list);
            return pageBean;
        }catch (Exception e){
            throw new MessageException("获取消息列表失败"+e.getMessage());
        }
    }

    @Override
    public Message getMes(String mesId) {
        try {
            return messageDao.getMes(mesId);
        }catch (Exception e){
            throw new MessageException("获取单个消息信息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void add(Message message) {
        try {
            messageDao.add(message);
        }catch (Exception e){
            throw new MessageException("添加单个消息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Message message) {
        try {
            messageDao.update(message);
        }catch (Exception e){
            throw new MessageException("更新单个消息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String mesId) {
        try {
            messageDao.delete(mesId);
        }catch (Exception e){
            throw new MessageException("删除单个消息失败"+e.getMessage());
        }
    }

    @Override
    public int getCount(Message message) {
        try{
           return messageDao.getCount(message);
        }catch (Exception e){
            throw new MessageException("获取消息总数失败!" + e.getMessage());
        }
    }
}
