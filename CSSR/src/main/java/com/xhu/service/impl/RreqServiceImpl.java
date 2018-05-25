package com.xhu.service.impl;

import com.xhu.dao.RecruitDao;
import com.xhu.dao.RreqDao;
import com.xhu.dao.RtypeDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.CodeEnum;
import com.xhu.entity.Rreq;
import com.xhu.entity.Rtype;
import com.xhu.exception.RreqException;
import com.xhu.service.RreqService;
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
public class RreqServiceImpl implements RreqService {
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private RreqDao rreqDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private RtypeDao rtypeDao;


    @Override
    public PageBean<Rreq> list(Integer currentPage, Integer pageSize, Rreq rreq) {
        try {
            if(rreq == null){
                throw new RreqException(CodeEnum.PARAMETER);
            }
            rreq.setFromTime(commonUtil.StringDateFormat(rreq.getFromTime()));
            rreq.setToTime(commonUtil.StringDateFormat(rreq.getToTime()));
            PageBean pageBean = new PageBean(pageSize,getCount(rreq),currentPage);
            List<Rreq> rreqs = rreqDao.list(pageBean.getIndex(),pageBean.getSize(),rreq);
            for (Rreq rreq1:rreqs) {
                rreq1.setUserMc(userDao.getUser(rreq1.getUserId()).getUserMc());
                rreq1.setRecruitName(rtypeDao.getName(recruitDao.queryById(rreq1.getRecruitId()).getRecruitType()));
                rreq1.setCreateTime(commonUtil.formatStringDate(rreq1.getCreateTime(),true));
            }
            pageBean.setDatas(rreqs);
            return pageBean;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Integer rreqId) {
        try {
            if(rreqId == null){
                throw new RreqException(CodeEnum.PARAMETER);
            }
            rreqDao.delete(rreqId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Rreq getRreq(Integer rreqId) {
        try {
            if(rreqId == null){
                throw new RreqException(CodeEnum.PARAMETER);
            }
            return rreqDao.getRreq(rreqId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(Rreq rreq) {
        try {
            if(rreq == null || rreq.getRreqId() == null){
                throw new RreqException(CodeEnum.PARAMETER);
            }
            rreq.setRreqState(1);
            rreqDao.update(rreq);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public void add(Rreq rreq) {
        try {
            if(rreq == null || rreq.getUserId()==null || rreq.getRecruitId() == null){
                throw new RreqException(CodeEnum.PARAMETER);
            }
            if(userDao.getUser(rreq.getUserId()).getCompanyId()!=null){
                throw new RreqException(CodeEnum.SREQADDUSERERROR);
            }
            Integer companyId = recruitDao.queryById(rreq.getRecruitId()).getCompanyId();
            rreq.setCompanyId(companyId);
            if(rreqDao.check(rreq)>0){
                throw new RreqException(CodeEnum.RERREQ);
            }
            rreq.setRreqState(0);
            rreq.setCreateTime(commonUtil.dateToString(new Date()));
            rreqDao.add(rreq);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int getCount(Rreq rreq) {
        try {
            return rreqDao.getCount(rreq);
        }catch (Exception e){
            throw e;
        }
    }
}
