package com.xhu.service.impl;

import com.xhu.dao.CompanyDao;
import com.xhu.dao.SreqDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.CodeEnum;
import com.xhu.entity.Sign;
import com.xhu.entity.Sreq;
import com.xhu.exception.SreqException;
import com.xhu.service.SignService;
import com.xhu.service.SreqService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/4/23.
 */
@Service
public class SreqServiceImpl implements SreqService {
    @Autowired
    private SreqDao sreqDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private SignService signService;

    @Override
    public PageBean<Sreq> list(Integer currentPage, Integer pageSize, Sreq sreq) {
        try {
            if(sreq == null){
                throw new SreqException(CodeEnum.SREQLISTPARAM);
            }
            sreq.setFromTime(commonUtil.StringDateFormat(sreq.getFromTime()));
            sreq.setToTime(commonUtil.StringDateFormat(sreq.getToTime()));
            PageBean pageBean = new PageBean(pageSize,getCount(sreq),currentPage);
            List<Sreq> sreqs = sreqDao.list(pageBean.getIndex(),pageBean.getSize(),sreq);
            for (Sreq sreq1:sreqs) {
                sreq1.setUserMc(userDao.getUser(sreq1.getUserId()).getUserMc());
                sreq1.setCompanyName(companyDao.getCompany(sreq1.getCompanyId()).getCompanyName());
                sreq1.setCreateTime(commonUtil.formatStringDate(sreq1.getCreateTime(),true));
                sreq1.setHandleTime(commonUtil.formatStringDate(sreq1.getHandleTime(),true));
            }
            pageBean.setDatas(sreqs);
            return pageBean;

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Integer srId) {
        try {
            if(srId == null){
                throw new SreqException(CodeEnum.SREQDELETEPARAM);
            }
            sreqDao.delete(srId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Sreq getLReq(Integer srId) {
        try {
            if(srId == null){
                throw new SreqException(CodeEnum.SREQGETPARAM);
            }
            return sreqDao.getSreq(srId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(Sreq sreq) {
        try {
            if(sreq == null || (sreq.getSrState() != 1&&sreq.getSrState() != 2)){
                throw new SreqException(CodeEnum.SREQUPDATEPARAM);
            }
            Sreq sreq1 = sreqDao.getSreq(sreq.getSrId());
            if(sreq1.getSrState() == 0){
                throw new SreqException(CodeEnum.SREQREUPDATE);
            }
            Date date = new Date();
            sreq.setHandleTime(commonUtil.dateToString(date));
            if(sreq.getSrState() == 1){//同意
                sreqDao.update(sreq);
                Sign sign = new Sign(sreq.getUserId(),sreq.getCompanyId(),sreq.getSrTime().toString());
                signService.add(sign);
            }
            if(sreq.getSrState() == 2){//拒绝
                sreqDao.update(sreq);
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void add(Sreq sreq) {
        try {
            if(sreq == null || sreq.getUserId() == null ||sreq.getCompanyId() == null){
                throw new SreqException(CodeEnum.SREQADDPARAM);
            }
            sreq.setSrState(0);
            if(sreqDao.check(sreq) > 0){
                throw new SreqException(CodeEnum.SREQREADD);
            }
            if(userDao.getUser(sreq.getUserId()).getCompanyId()!=null){
                throw new SreqException(CodeEnum.SREQADDUSERERROR);
            }
            sreq.setCreateTime(commonUtil.dateToString(new Date()));
            sreqDao.add(sreq);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int getCount(Sreq sreq) {
        try {
            return sreqDao.getCount(sreq);
        }catch (Exception e){
            throw e;
        }
    }
}
