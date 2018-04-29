package com.xhu.service.impl;

import com.xhu.dao.*;
import com.xhu.dto.PageBean;
import com.xhu.entity.CodeEnum;
import com.xhu.entity.LReq;
import com.xhu.entity.Lease;
import com.xhu.entity.Tool;
import com.xhu.exception.LReqException;
import com.xhu.service.LReqService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/3/30.
 */
@Service
public class LReqServiceImpl implements LReqService {

    @Autowired
    private LReqDao lReqDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private ToolDao toolDao;
    @Autowired
    private LeaseDao leaseDao;

    @Override
    public PageBean<LReq> list(Integer currentPage, Integer pageSize, LReq lReq) {
        try {
            if(lReq == null){
                throw new LReqException(CodeEnum.PARAMETER);
            }
            lReq.setFromTime(commonUtil.StringDateFormat(lReq.getFromTime()));
            lReq.setToTime(commonUtil.StringDateFormat(lReq.getToTime()));
            PageBean pageBean = new PageBean(pageSize,getCount(lReq),currentPage);
            List<LReq> lReqs = lReqDao.list(pageBean.getIndex(),pageBean.getSize(),lReq);
            for (LReq lreq:lReqs) {
                lreq.setToolName(toolDao.getTool(lreq.getToolId()).getToolName());
                lreq.setUserMc(userDao.getUser(lreq.getUserId()).getUserMc());
                lreq.setlReqCreateTime(commonUtil.formatStringDate(lreq.getlReqCreateTime(),true));
                lreq.setlReqHandleTime(commonUtil.formatStringDate(lreq.getlReqHandleTime(),true));
            }
            pageBean.setDatas(lReqs);
            return pageBean;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Integer lReqId) {
        try {
            if(lReqId == null){
                throw new LReqException(CodeEnum.PARAMETER);
            }
            lReqDao.delete(lReqId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public LReq getLReq(Integer lReqId) {
        try {
            if(lReqId == null){
                throw new LReqException(CodeEnum.PARAMETER);
            }
            return lReqDao.getLReq(lReqId);
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    @Transactional
    public void updateResult(LReq lReq,boolean isOver) {
        try{
            if(lReq == null){
                throw new LReqException(CodeEnum.PARAMETER);
            }
            Date date = new Date();
            lReq.setlReqHandleTime(commonUtil.dateToString(date));
            if("1".equals(lReq.getlReqResult())){
                Tool tool = new Tool();
                tool.setToolId(lReq.getToolId());
                if(isOver){
                    Lease lease = leaseDao.getLeaseByUser(lReq.getToolId(),lReq.getUserId());
                    if(lease==null){
                        throw new LReqException(CodeEnum.LEASENULL);
                    }
                    lease.setLeaseState("2");
                    tool.setToolState("1");
                    leaseDao.update(lease);
                }else {
                    tool.setToolState("2");
                    int result = toolDao.update(tool);
                    if (result < 1) {
                        throw new LReqException(CodeEnum.TOOLSTATE);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(lReq.getlReqTerm()));
                    Lease lease = new Lease(lReq.getUserId(), lReq.getToolId(), lReq.getlReqContent(), lReq.getlReqMoney(), lReq.getlReqTerm(), "1", commonUtil.dateToString(date));
                    lease.setCompanyId(lReq.getCompanyId());
                    lease.setEndTime(commonUtil.dateToString(calendar.getTime()));
                    lease.setTotalMoney(Float.valueOf(String.format("%.1f", lease.getLeaseRepay() * Integer.parseInt(lease.getLeaseTerm()))));
                    leaseDao.add(lease);
                }
            }
            lReqDao.update(lReq);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void add(LReq lReq) {
        try {
            if(lReq.getToolId()==null || lReq.getUserId() == null){
                throw new LReqException(CodeEnum.PARAMETER);
            }
            if(lReqDao.check(lReq.getUserId(),lReq.getToolId())>0){
                throw new LReqException(CodeEnum.REREQL);
            }
            Tool tool = toolDao.getTool(lReq.getToolId());
            if(!"1".equals(tool.getToolState())){
                throw new LReqException(CodeEnum.TOOLLEASED);
            }
            lReq.setCompanyId(tool.getCompanyId());
            lReq.setlReqCreateTime(commonUtil.dateToString(new Date()));
            lReqDao.add(lReq);
            commonUtil.sendMessage("401");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int getCount(LReq lReq) {
        try {
            return lReqDao.getCount(lReq);
        }catch (Exception e){
            throw new LReqException(CodeEnum.LREQCOUNT);
        }
    }
}
