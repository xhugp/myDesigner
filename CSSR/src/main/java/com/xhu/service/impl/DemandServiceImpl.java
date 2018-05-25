package com.xhu.service.impl;

import com.xhu.dao.DemandDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.CodeEnum;
import com.xhu.entity.Demand;
import com.xhu.exception.DemandException;
import com.xhu.service.DemandService;
import com.xhu.util.CommonUtil;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/3/15.
 */
@Service
public class DemandServiceImpl implements DemandService {
    @Autowired
    private DemandDao demandDao;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public PageBean<Demand> list(Integer currentPage,Integer pageSize,Demand demand) {
        demand.setFromTime(commonUtil.StringDateFormat(demand.getFromTime()));
        demand.setToTime(commonUtil.StringDateFormat(demand.getToTime()));
        int count = getCount(demand);
        try {
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List<Demand> demands = demandDao.list(pageBean.getIndex(),pageBean.getSize(),demand);
            for (Demand demand1: demands ) {
                demand1.setTypeName(commonUtil.getDtypeName(demand1.getDemandType()));
                demand1.setCreateTime(commonUtil.formatStringDate(demand1.getCreateTime(),true));
                demand1.setLastUpdateTime(commonUtil.formatStringDate(demand1.getLastUpdateTime(),true));
            }
            pageBean.setDatas(demands);
            return pageBean;
        }catch (Exception e){
            throw new DemandException(CodeEnum.DEMANDLIST);
        }
    }

    @Override
    public Demand queryById(String demandId) {
        try {
            Demand demand = demandDao.queryById(demandId);
            demand.setTypeName(commonUtil.getDtypeName(demand.getDemandType()));
            demand.setCreateTime(commonUtil.formatStringDate(demand.getCreateTime(),true));
            demand.setLastUpdateTime(commonUtil.formatStringDate(demand.getLastUpdateTime(),true));
            return demand;
        }catch (Exception e){
            throw new DemandException(CodeEnum.DEMANDADD);
        }
    }

    @Override
    @Transactional
    public void add(Demand demand) {
        try {
            demand.setCreateTime(commonUtil.dateToString(new Date()));
            demand.setLastUpdateTime(commonUtil.dateToString(new Date()));
            //将需求状态至于已发布
            demand.setDemandState("1");
            demandDao.add(demand);
        }catch (Exception e){
            throw new DemandException("插入需求信息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Demand demand) {
        try {
            demand.setLastUpdateTime(commonUtil.dateToString(new Date()));
            demandDao.update(demand);
        }catch (Exception e){
            throw new DemandException(CodeEnum.DEMANDDELETE);
        }

    }

    @Override
    @Transactional
    public void deleteById(String demandId) {
        try {
            demandDao.deleteById(demandId);
        }catch (Exception e){
            throw new DemandException("删除需求信息失败"+e.getMessage());
        }

    }



    @Override
    @Transactional
    public void deleteBatch(String deleteIds) {
        String[] ids = deleteIds.split(",");
        List<String> deleteList = new ArrayList<String>();
        for (String str:ids) {
            deleteList.add(str);
        }
        try {
            demandDao.deleteBatch(deleteList);
        }catch (Exception e){
            throw new DemandException(CodeEnum.DEMANDDELETEBATCH);
        }
    }

    @Override
    public int getCount(Demand demand) {
        try{
            return demandDao.getCount(demand);
        }catch (Exception e){
            throw new DemandException(CodeEnum.DEMANDCOUNT);
        }
    }

    @Override
    @Transactional
    public void reUpdate(Demand demand) {
        try {
            if(demand.getDemandId() == null || !"5".equals(demand.getDemandState())){
                throw new DemandException(CodeEnum.DEMANDRELOADERROR);
            }
            demandDao.deleteById(demand.getDemandId());
            demand.setDemandId(null);
            demand.setCreateTime(commonUtil.dateToString(new Date()));
            demand.setLastUpdateTime(commonUtil.dateToString(new Date()));
            //将需求状态至于已发布
            demand.setDemandState("1");
            demandDao.add(demand);
        }catch (Exception e){
            throw new DemandException(CodeEnum.REUPDATE);
        }
    }

}
