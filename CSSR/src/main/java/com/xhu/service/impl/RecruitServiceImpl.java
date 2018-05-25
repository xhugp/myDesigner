package com.xhu.service.impl;

import com.xhu.dao.RecruitDao;
import com.xhu.dao.RtypeDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Recruit;
import com.xhu.service.RecruitService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/5/22.
 */
@Service
public class RecruitServiceImpl implements RecruitService{
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private RtypeDao rtypeDao;
    @Autowired
    private RecruitDao recruitDao;

    @Override
    @Transactional
    public void add(Recruit recruit) {
        try {
            recruit.setCreateTime(commonUtil.StringDateFormat(recruit.getCreateTime()));
            recruit.setEndTime(commonUtil.StringDateFormat(recruit.getEndTime()));
            recruitDao.add(recruit);
        }catch (Exception e){
            throw new RuntimeException("添加招聘信息错误");
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer recruitId) {
        try {
            recruitDao.deleteById(recruitId);
        }catch (Exception e){
            throw new RuntimeException("删除招聘信息错误");
        }
    }

    @Override
    public PageBean<Recruit> list(Integer currentPage, Integer pageSize, Recruit recruit) {
        recruit.setTime(commonUtil.dateToString(new Date()));
        int count = getCount(recruit);
        try {
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List<Recruit> recruits = recruitDao.list(pageBean.getIndex(),pageBean.getSize(),recruit);
            for (Recruit recruit1: recruits ) {
                recruit1.setTypeName(rtypeDao.getName(recruit1.getRecruitType()));
                recruit1.setCreateTime(commonUtil.formatStringDate(recruit1.getCreateTime(),false));
                recruit1.setEndTime(commonUtil.formatStringDate(recruit1.getEndTime(),false));
            }
            pageBean.setDatas(recruits);
            return pageBean;
        }catch (Exception e){
            throw new RuntimeException("分页查询错误"+e.getMessage());
        }
    }

    @Override
    public int getCount(Recruit recruit) {
        try{
            return recruitDao.getCount(recruit);
        }catch (Exception e){
            throw new RuntimeException("查询招聘数量错误"+e.getMessage());
        }
    }

    @Override
    public Recruit queryById(Integer recruitId) {
        try{
            return recruitDao.queryById(recruitId);
        }catch (Exception e){
            throw new RuntimeException("获取招聘信息错误");
        }
    }
}
