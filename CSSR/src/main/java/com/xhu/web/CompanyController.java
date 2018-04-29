package com.xhu.web;

import com.xhu.dto.ResultBean;
import com.xhu.entity.Company;
import com.xhu.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gp on 2018/3/25.
 */
@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/get-company/{companyId}",method = RequestMethod.GET)
    private ResultBean getCom(@PathVariable  Integer companyId){
        return new ResultBean("200",companyService.getCompany(companyId),"汽修厂信息");
    }

    @RequestMapping(value = "/company/update",method = RequestMethod.POST)
    private ResultBean update(@RequestBody Company company){
        companyService.update(company);
        return new ResultBean("200","修改成功");
    }
}
