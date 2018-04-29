package com.xhu.service;

import com.xhu.entity.Company;

/**
 * Created by gp on 2018/3/22.
 */
public interface CompanyService {
    public Company getCompany(Integer companyId);

    public void update(Company company);

}
