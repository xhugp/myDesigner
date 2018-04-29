package com.xhu.service.impl;

import com.xhu.dao.OrderDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.CodeEnum;
import com.xhu.entity.Order;
import com.xhu.entity.User;
import com.xhu.exception.UserException;
import com.xhu.service.UserService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gp on 2018/3/21.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private OrderDao orderDao;


    @Override
    public boolean login(User user) {
        try {
            if(user.getUserName()== null || user.getUserPwd() == null){
                throw new UserException(CodeEnum.EMPTYUSER);
            }
            User user1 = userDao.getUserByName(user.getUserName());
            if(user1 == null){
                throw new UserException(CodeEnum.NONUSER);
            }else {
                if (!user.getUserPwd().equals(user1.getUserPwd())) {
                    throw new UserException(CodeEnum.PWDERROR);
                } else {
                    return true;
                }
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public PageBean<User> list(Integer currentPage,Integer pageSize,User user) {
        try {
            if (user == null) {
                user = new User();
            }
            int count = getCount(user);
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List<User> users = userDao.list(pageBean.getIndex(),pageBean.getSize(),user);
            for (User newUser: users) {
                newUser.setUserPwd(commonUtil.EncoderByMd5(newUser.getUserPwd()));
            }
            pageBean.setDatas(users);
            return pageBean;
        }catch (Exception e){
            throw  e;
        }
    }

    @Override
    public PageBean<User> latelyUsers(Integer currentPage, Integer pageSize, Integer companyId) {
        try {
            User user = new User();
            user.setCompanyId(companyId);
            Order order = new Order();
            order.setCompanyId(companyId);
            order.setOrderState("2");
            List<Integer> list = orderDao.userId_list(order);
            PageBean pageBean = new PageBean(pageSize,list.size(),currentPage);
            if(list.size() < 1){
                pageBean.setDatas(null);
                return pageBean;
            }
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("index",pageBean.getIndex());
            map.put("size",pageBean.getSize());
            map.put("userIds",list);
            List<User> users = userDao.list_lately(map);
            for (User newUser: users) {
                newUser.setUserPwd(commonUtil.EncoderByMd5(newUser.getUserPwd()));
            }
            pageBean.setDatas(users);
            return pageBean;
        }catch (Exception e){
            throw  e;
        }
    }

    @Override
    @Transactional
    public void add(User user) {
        try {
            if(user.getUserName() == null && user.getUserPwd() == null){
                throw new UserException(CodeEnum.PARAMETER);
            }
            int result = userDao.add(user);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        try {
            if(user.getUserId() == null){
                throw new UserException(CodeEnum.PARAMETER);
            }
            userDao.update(user);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    @Transactional
    public void delete(Integer userId) {
        try {

            if(userId == null){
                throw new UserException(CodeEnum.PARAMETER);
            }
            userDao.delete(userId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public User getUser(Integer userId) {
        try {
            if(userId == null){
                throw new UserException(CodeEnum.PARAMETER);
            }
            User user = userDao.getUser(userId);
            if(null != user){
                user.setUserPwd(commonUtil.EncoderByMd5(user.getUserPwd()));
                return user;
            }else {
                throw new UserException(CodeEnum.NONUSER);
            }
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public int getCount(User user) {
        try {
            return userDao.getCount(user);
        }catch (Exception e){
            throw new UserException(CodeEnum.USERCOUNT);
        }

    }

}
