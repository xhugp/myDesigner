package com.xhu.dao;

import com.xhu.entity.Company;

/**
 * Created by gp on 2018/3/20.
 */
public interface CompanyDao {
     public  Company getCompany(Integer companyId);

     public int update(Company company);


}
