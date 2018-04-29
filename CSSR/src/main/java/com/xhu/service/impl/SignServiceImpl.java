package com.xhu.service.impl;

import com.xhu.dao.CompanyDao;
import com.xhu.dao.SignDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Company;
import com.xhu.entity.Sign;
import com.xhu.entity.User;
import com.xhu.exception.SignException;
import com.xhu.service.SignService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/3/28.
 */
@Service
public class SignServiceImpl implements SignService {

    @Autowired
    private SignDao signDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public Sign getSign(Sign sign) {
        try {
            if(sign.getUserId() == null && sign.getCompanyId() == null){
                throw new SignException("查询条件不存在");
            }
            Sign sign1 = signDao.getSign(sign);
            sign1.setCompanyName(companyDao.getCompany(sign1.getCompanyId()).getCompanyName());
            sign1.setUserMc(userDao.getUser(sign1.getUserId()).getUserMc());
            sign1.setSignDate(commonUtil.formatStringDate(sign1.getSignDate(),false));
            sign1.setEndDate(commonUtil.formatStringDate(sign1.getEndDate(),false));
            return sign1;
        }catch (Exception e){
            throw new SignException("获取签约信息失败" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void add(Sign sign) {
        try {
            if(sign.getUserId() == null && sign.getCompanyId() == null){
                throw new SignException("参数错误");
            }
            User user = new User();
            user.setUserId(sign.getUserId());
            user.setCompanyId(sign.getCompanyId());
            userDao.update(user);
            sign.setSignDate(commonUtil.dateToString(new Date()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.YEAR,Integer.valueOf(sign.getSignTime()));
            sign.setEndDate(commonUtil.dateToString(calendar.getTime()));
            signDao.add(sign);
        }catch (Exception e){
            throw new SignException("添加签约信息失败！"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Sign sign) {
        try {
            if(sign.getUserId() == null && sign.getCompanyId() == null){
                throw new SignException("参数错误");
            }
            User user = new User();
            user.setUserId(sign.getUserId());
            user.setCompanyId(null);
            userDao.update(user);
            signDao.delete(sign);
        }catch (Exception e){
            throw new SignException("解除签约失败！"+e.getMessage());
        }
    }

    @Override
    public Company getCompany(Integer userId) {
        try{
            if(userId == null){
                throw new SignException("参数错误");
            }
            Sign sign = new Sign();
            sign.setUserId(userId);
            Sign sign1 = signDao.getSign(sign);
            if(sign1.getCompanyId() == null){
                throw new SignException("该用户还未签约");
            }
            return companyDao.getCompany(sign1.getCompanyId());
        }catch (Exception e){
            throw new SignException("查询用户签约公司信息失败" + e.getMessage());
        }
    }

    @Override
    public PageBean<Sign> getUsers(Integer currentPage, Integer pageSize, Sign sign) {
        try {
            if(sign.getCompanyId() == null){
                throw new SignException("参数错误");
            }
            int totalSize = signDao.getCount(sign);
            PageBean pageBean = new PageBean(pageSize,totalSize,currentPage);
            List<Sign> signs_list = signDao.list(pageBean.getIndex(),pageBean.getSize(),sign);
            for (Sign sign1:signs_list) {
                sign1.setUserMc(userDao.getUser(sign1.getUserId()).getUserMc());
                sign1.setSignDate(commonUtil.formatStringDate(sign1.getSignDate(),false));
                sign1.setEndDate(commonUtil.formatStringDate(sign1.getEndDate(),false));
            }
            pageBean.setDatas(signs_list);
            return pageBean;
        }catch (Exception e){
            throw new SignException("获取公司签约员工列表失败" + e.getMessage());
        }
    }
}
