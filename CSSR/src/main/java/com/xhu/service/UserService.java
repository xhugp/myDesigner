package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.User;

import java.util.List;

/**
 * Created by gp on 2018/3/21.
 */
public interface UserService {
    public PageBean<User> list(Integer currentPage,Integer size,User user);

    public PageBean<User> latelyUsers(Integer currentPage,Integer size,Integer companyId);

    public void add(User user);

    public void update(User user);

    public void delete(Integer userId);

    public User getUser(Integer userId);

    public int getCount(User user);

    public boolean login(User user);



}
