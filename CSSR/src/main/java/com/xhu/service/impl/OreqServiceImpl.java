package com.xhu.service.impl;

import com.xhu.dao.DemandDao;
import com.xhu.dao.OrderDao;
import com.xhu.dao.OreqDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.CodeEnum;
import com.xhu.entity.Demand;
import com.xhu.entity.Order;
import com.xhu.entity.Oreq;
import com.xhu.exception.OreqException;
import com.xhu.service.OreqService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/4/4.
 */
@Service
public class OreqServiceImpl implements OreqService{
    @Autowired
    private OreqDao oreqDao;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DemandDao demandDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public PageBean<Oreq> list(Integer currentPage, Integer pageSize, Oreq oreq) {
        try {
            if(oreq == null){
                throw new OreqException(CodeEnum.PARAMETER);
            }
            oreq.setFromTime(commonUtil.StringDateFormat(oreq.getFromTime()));
            oreq.setToTime(commonUtil.StringDateFormat(oreq.getToTime()));
            PageBean pageBean = new PageBean(pageSize,getCount(oreq),currentPage);
            List<Oreq> oreqs = oreqDao.list(pageBean.getIndex(),pageBean.getSize(),oreq);
            for (Oreq oreq1:oreqs) {
                oreq1.setUserMc(userDao.getUser(oreq1.getUserId()).getUserMc());
                oreq1.setOreqCreateTime(commonUtil.formatStringDate(oreq1.getOreqCreateTime(),true));
                oreq1.setOreqHandleTime(commonUtil.formatStringDate(oreq1.getOreqHandleTime(),true));
            }
            pageBean.setDatas(oreqs);
            return pageBean;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Integer oreqId) {
        try {
            if(oreqId == null){
                throw new OreqException(CodeEnum.PARAMETER);
            }
            oreqDao.delete(oreqId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Oreq getOreq(Integer oreqId) {
        try {
            if(oreqId == null){
                throw new OreqException(CodeEnum.PARAMETER);
            }
            return oreqDao.getOreq(oreqId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateResult(Oreq oreq,boolean isOver) {
        try{
            if(oreq == null){
                throw new OreqException(CodeEnum.PARAMETER);
            }
            String date = commonUtil.dateToString(new Date());
            oreq.setOreqHandleTime(date);
            Order order = orderDao.getOrder(oreq.getOrderId());
            Demand demand = demandDao.queryById(order.getDemandId());
            if("1".equals(oreq.getOreqResult())){
                if(isOver){
                    demand.setDemandState("3");//需求结束
                    order.setOrderState("2");//订单结束
                }else {
                    demand.setDemandState("5");//将需求状态切成撤销
                    order.setOrderState("4");//将订单状态切成取消
                }
                order.setLastUpdateTime(date);
                demand.setLastUpdateTime(date);
                orderDao.update(order);
                demandDao.update(demand);
            }
            oreqDao.update(oreq);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public void add(Oreq oreq) {
        try {
            if(oreq.getOrderId()==null || oreq.getOreqType() == null){
                throw new OreqException(CodeEnum.PARAMETER);
            }
            if(oreqDao.check(oreq.getUserId(),oreq.getOrderId())>0){
                throw new OreqException(CodeEnum.REOREQ);
            }
            String date = commonUtil.dateToString(new Date());
            Order order = orderDao.getOrder(oreq.getOrderId());
            if("2".equals(oreq.getOreqType())) {
                order.setOrderState("3");//将订单状态切成中断
                order.setLastUpdateTime(date);
                Demand demand = demandDao.queryById(order.getDemandId());
                demand.setLastUpdateTime(date);
                demand.setDemandState("4");//将需求状态切成中断
                demandDao.update(demand);
                orderDao.update(order);
            }
            oreq.setCompanyId(order.getCompanyId());
            oreq.setOreqCreateTime(date);
            oreqDao.add(oreq);
            commonUtil.sendMessage("402");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int getCount(Oreq oreq) {
        try {
            return oreqDao.getCount(oreq);
        }catch (Exception e){
            throw new OreqException(CodeEnum.OREQCOUNT);
        }
    }
}
