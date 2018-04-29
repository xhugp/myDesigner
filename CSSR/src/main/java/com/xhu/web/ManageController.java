package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Manage;
import com.xhu.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gp on 2018/3/8.
 */
@RestController
@RequestMapping("/api")
public class ManageController {
    @Autowired
    private ManageService manageService;

    @RequestMapping(value = "/manage/list",method = RequestMethod.GET)
    private ResultBean list(@RequestBody Integer currentPage,@RequestBody Integer size){
        PageBean pageBean1 = manageService.list(currentPage,size);
        return new ResultBean("200",pageBean1,"管理员信息");
    }

    @RequestMapping(value = "/manage/login",method = RequestMethod.POST)
    private ResultBean login(@RequestBody Manage manage){
        System.out.print(manage.getManageName()+"....."+manage.getManagePwd());
        Manage manage1 = manageService.login(manage.getManageName(),manage.getManagePwd());
        return new ResultBean("200",manage1,"登陆成功");
    }

    @RequestMapping(value = "/manage/get-by-id/{manageId}",method = RequestMethod.GET)
    private ResultBean queryById(@PathVariable("manageId") Integer manageId){
       Manage manage =  manageService.queryById(manageId);
       return new ResultBean("200",manage,"管理员信息");
    }

    @RequestMapping(value = "/manage/get-by-name/{manageName}",method = RequestMethod.GET)
    private ResultBean queryByName(@PathVariable("manageName") String manageName){
        Manage manage = manageService.queryByName(manageName);
        return new ResultBean("200",manage,"管理员信息");
    }

    @RequestMapping(value = "/manage/{companyId}/{currentPage}",method = RequestMethod.GET)
    private ResultBean queryByCompany(@PathVariable("companyId") Integer companyId,@PathVariable("currentPage") Integer currentPage){
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        PageBean pageBean1 = manageService.queryByCompany(companyId,pageBean);
        return new ResultBean("200",pageBean1,"管理员信息");
    }

    @RequestMapping(value = "/manage/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Manage manage){
        return new ResultBean("200",manageService.add(manage),"添加成功");
    }

    @RequestMapping(value = "/manage/update",method = RequestMethod.POST)
    private ResultBean update(@RequestBody Manage manage){
        return new ResultBean("200",manageService.update(manage),"更新成功");
    }

    @RequestMapping(value = "/manage/delete/{manageId}",method = RequestMethod.POST)
    private ResultBean deleteById(@PathVariable("manageId") Integer manageId){
        return new ResultBean("200",manageService.deleteById(manageId),"删除成功");
    }

    @RequestMapping(value = "/manage/deletebatch",method = RequestMethod.POST)
    private ResultBean deleteBatch(String manageIds){
        return new ResultBean("200",manageService.deleteBatch(manageIds),"批量删除成功");
    }

}
