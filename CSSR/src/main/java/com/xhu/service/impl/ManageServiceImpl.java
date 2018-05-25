package com.xhu.service.impl;

import com.xhu.dao.CompanyDao;
import com.xhu.dao.ManageDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Company;
import com.xhu.entity.Manage;
import com.xhu.exception.ManageException;
import com.xhu.service.ManageService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/3/8.
 */
@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    private ManageDao manageDao;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private CompanyDao companyDao;

    @Override
    public PageBean<Manage> list(Integer currentPage,Integer size) {
        PageBean pageBean = new PageBean(size,manageDao.getCount(1),currentPage);
        List<Manage> list = manageDao.list(pageBean.getIndex(),pageBean.getSize());
        pageBean.setDatas(list);
        return pageBean;
    }

    @Override
    public Manage queryById(Integer manageId) {
        Manage manage = manageDao.queryById(manageId);
        manage.setLastLoginTime(commonUtil.formatStringDate(manage.getLastLoginTime(),true));
        manage.setCreateTime(commonUtil.formatStringDate(manage.getCreateTime(),true));
        manage.setManagePwd(commonUtil.EncoderByMd5(manage.getManagePwd()));
        manage.setCompanyName(companyDao.getCompany(manage.getCompanyId()).getCompanyName());
        return manage;
    }

    @Override
    public Manage queryByName(String manageName) {
        return manageDao.queryByName(manageName);
    }

    @Override
    public Manage login(String manageName, String managePwd) {
        Manage manage = queryByName(manageName);
        if(null != manage && null != manage.getManagePwd()){
            if(managePwd.equals(manage.getManagePwd())){
                manage.setLastLoginTime(commonUtil.formatStringDate(manage.getLastLoginTime(),true));
                Manage manage1 = new Manage();
                manage1.setManageId(manage.getManageId());
                manage1.setLastLoginTime(commonUtil.dateToString(new Date()));
                manageDao.update(manage1);
                manage.setManagePwd(commonUtil.EncoderByMd5(manage.getManagePwd()));
                return manage;
            }else{
                throw new ManageException("密码错误！");
            }
        }else{
            throw new ManageException("不存在该管理员！");
        }
    }

    @Override
    public PageBean<Manage> queryByCompany(int companyId,PageBean pageBean) {
        pageBean.setTotalSize( manageDao.getCount(companyId));
        List<Manage> list = manageDao.queryByCompany(companyId,pageBean);
        pageBean.setDatas(list);
        return pageBean;
    }

    @Override
    @Transactional
    public boolean add(Manage manage) {

        if(manageDao.queryByName(manage.getManageName())!=null){
            throw new ManageException("账号重复！");
        }
        manage.setCreateTime(commonUtil.dateToString(new Date()));
        manage.setLastLoginTime(commonUtil.dateToString(new Date()));
        manage.setManagePwd("123456");//设置初始密码

        int result = manageDao.add(manage);
        if(result > 0){
            return true;
        }else{
            throw new ManageException("添加管理员信息失败！");
        }
    }

    @Override
    @Transactional
    public boolean update(Manage manage) {
        int result = manageDao.update(manage);
        if(result > 0){
            return true;
        }else{
            throw new ManageException("更新管理员信息失败！");
        }
    }

    @Override
    @Transactional
    public boolean deleteById(int manageId) {
        int result = manageDao.deleteById(manageId);
        if(result > 0){
            return true;
        }else{
            throw new ManageException("删除管理员信息失败！");
        }
    }

    @Override
    @Transactional
    public int deleteBatch(String manageIds) {
        String[] ids = manageIds.split(",");
        List<String> deleteList = new ArrayList<String>();
        for (String str:ids) {
            deleteList.add(str);
        }
        int result = manageDao.deleteBatch(deleteList);
        if(result > 0){
            return result;
        }else {
            throw new ManageException("批量删除失败！");
        }
    }
}
