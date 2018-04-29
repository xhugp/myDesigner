package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Manage;

import java.util.List;

/**
 * Created by gp on 2018/3/8.
 */
public interface ManageService {
    public PageBean<Manage> list(Integer currentPage,Integer size);

    public Manage queryById(Integer manageId);

    public Manage queryByName(String manageName);

    public Manage login(String manageName,String managePwd);

    public PageBean<Manage> queryByCompany(int companyId,PageBean pageBean);

    public boolean add(Manage manage);

    public boolean update(Manage manage);

    public boolean deleteById(int manageId);

    public int deleteBatch(String manageIds);
}
