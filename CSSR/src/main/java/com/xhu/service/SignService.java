package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Company;
import com.xhu.entity.Sign;
import com.xhu.entity.User;

import java.util.List;

/**
 * Created by gp on 2018/3/28.
 */
public interface SignService {
    public Sign getSign(Sign sign);

    public void add(Sign sign);

    public void delete(Sign sign);

    public Company getCompany(Integer userId);

    public PageBean<Sign> getUsers(Integer currentPage, Integer pageSize, Sign sign);
}
