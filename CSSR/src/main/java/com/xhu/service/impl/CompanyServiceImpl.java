package com.xhu.service.impl;

import com.xhu.dao.CompanyDao;
import com.xhu.entity.Company;
import com.xhu.exception.CommonException;
import com.xhu.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gp on 2018/3/22.
 */
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyDao companyDao;

    @Override
    public Company getCompany(Integer companyId) {
        try {
            return companyDao.getCompany(companyId);
        }catch (Exception e){
            throw new CommonException("查询公司信息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Company company) {
        try {
            companyDao.update(company);
        }catch (Exception e){
            throw new CommonException("公司信息更新失败"+e.getMessage());
        }
    }
}
