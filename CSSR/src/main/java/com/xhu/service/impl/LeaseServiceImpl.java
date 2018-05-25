package com.xhu.service.impl;

import com.xhu.dao.LeaseDao;
import com.xhu.dao.ToolDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Lease;
import com.xhu.entity.Tool;
import com.xhu.exception.LeaseException;
import com.xhu.service.LeaseService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/3/22.
 */
@Service
public class LeaseServiceImpl implements LeaseService {
    @Autowired
    private LeaseDao leaseDao;
    @Autowired
    private ToolDao toolDao;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private UserDao userDao;

    @Override
    public PageBean<Lease> list(Integer currentPage, Integer pageSize, Lease lease) {
        int count = getCount(lease);
        try {
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List<Lease> list = leaseDao.list(pageBean.getIndex(),pageBean.getSize(),lease);
            for (Lease lease1:list) {
                lease1.setState(commonUtil.getLeaseState(lease1.getLeaseState()));
                lease1.setUserMc(userDao.getUser(lease1.getUserId()).getUserMc());
                lease1.setToolName(toolDao.getTool(lease1.getToolId()).getToolName());
                lease1.setCreateTime(commonUtil.formatStringDate(lease1.getCreateTime(),false));
                lease1.setEndTime(commonUtil.formatStringDate(lease1.getEndTime(),false));
            }
            pageBean.setDatas(list);
            return pageBean;
        }catch (Exception e){
            throw new LeaseException("获取租赁列表失败"+e.getMessage());
        }
    }

    @Override
    public Lease getLease(String leaseId) {
        try {
            Lease lease = leaseDao.getLease(leaseId);
            if(lease == null){
                throw new LeaseException("未找到租赁信息");
            }
            lease.setState(commonUtil.getLeaseState(lease.getLeaseState()));
            lease.setUserMc(userDao.getUser(lease.getUserId()).getUserMc());
            lease.setToolName(toolDao.getTool(lease.getToolId()).getToolName());
            lease.setCreateTime(commonUtil.formatStringDate(lease.getCreateTime(),false));
            lease.setEndTime(commonUtil.formatStringDate(lease.getEndTime(),false));
            return lease;
        }catch (Exception e){
            throw new LeaseException("获取单个租赁信息失败"+e.getMessage());
        }
    }

    @Override
    public Lease getLeaseByTool(Integer toolId) {
        try {
            Lease ll = new Lease();
            ll.setLeaseState("1");
            ll.setToolId(toolId);
            Lease lease = leaseDao.getLeaseByTool(ll);
            if(lease == null){
                throw new LeaseException("未找到该工具租赁信息");
            }
            lease.setUserMc(userDao.getUser(lease.getUserId()).getUserMc());
            lease.setCreateTime(commonUtil.formatStringDate(lease.getCreateTime(),false));
            return lease;
        }catch (Exception e){
            throw new LeaseException("获取单个租赁信息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void add(Lease lease) {
        try {
            if(lease.getUserId() == null || lease.getToolId() == null){
                throw new LeaseException("参数错误");
            }
            Tool tool = toolDao.getTool(lease.getToolId());
            tool.setToolState("2");
            lease.setCreateTime(commonUtil.dateToString(new Date()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.YEAR,Integer.valueOf(lease.getLeaseTerm()));
            lease.setEndTime(commonUtil.dateToString(calendar.getTime()));
            leaseDao.add(lease);
            toolDao.update(tool);
        }catch (Exception e){
            throw new LeaseException("添加租赁信息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Lease lease,boolean isOver) {
        try {
            //此处调用支付接口
            /////////////////
            if (lease.getLeaseId() == null || lease.getUserId() == null) {
                throw new LeaseException("更新参数错误");
            }
            Lease lease1 = leaseDao.getLease(lease.getLeaseId());
            if(lease1 == null){
                throw new LeaseException("未找到租赁信息");
            }
            if(!"1".equals(lease1.getLeaseState())){
                throw new LeaseException("该租赁已经结束");
            }
            if(!lease1.getUserId().equals(lease.getUserId())){
                throw new LeaseException("无权提交");
            }
            if (isOver){
                Tool tool = toolDao.getTool(lease1.getToolId());
                tool.setToolState("1");
                toolDao.update(tool);
                lease1.setLeaseState("2");
            }
            leaseDao.update(lease1);
        }catch (Exception e){
            throw new LeaseException("更新租赁信息失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateOver(Lease lease) {
        try {
            update(lease,true);
        }catch (Exception e){
            throw new LeaseException("结束订单失败"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String leaseId) {
        try {
            Tool tool = toolDao.getTool(leaseDao.getLease(leaseId).getToolId());
            tool.setToolState("1");
            leaseDao.delete(leaseId);
            toolDao.update(tool);
        }catch (Exception e){
            throw new LeaseException("删除租赁信息失败"+e.getMessage());
        }
    }

    @Override
    public int getCount(Lease lease) {
        try {
            return leaseDao.getCount(lease);
        }catch (Exception e){
            throw new LeaseException("获取租赁信息总数失败" + e.getMessage());
        }

    }


}
