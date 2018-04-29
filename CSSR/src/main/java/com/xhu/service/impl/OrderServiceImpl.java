package com.xhu.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xhu.dao.DemandDao;
import com.xhu.dao.OrderDao;
import com.xhu.dao.UserDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Demand;
import com.xhu.entity.Order;
import com.xhu.entity.Oreq;
import com.xhu.entity.User;
import com.xhu.exception.OrderException;
import com.xhu.service.OrderService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gp on 2018/3/22.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private DemandDao demandDao;
    @Autowired
    private UserDao userDao;


    @Override
    public PageBean<Order> list(Integer currentPage, Integer pageSize, Order order) {
        try {
            if(order == null){
                order = new Order();
            }
            order.setFromTime(commonUtil.StringDateFormat(order.getFromTime()));
            order.setToTime(commonUtil.StringDateFormat(order.getToTime()));
            int count = getCount(order);
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List<Order> list = orderDao.list(pageBean.getIndex(),pageBean.getSize(),order);
            for(Order order1 : list){
                order1.setUserMc(userDao.getUser(order1.getUserId()).getUserMc());
                order1.setState(commonUtil.getOrderState(order1.getOrderState()));
                order1.setCreateTime(commonUtil.formatStringDate(order1.getCreateTime(),true));
                order1.setLastUpdateTime(commonUtil.formatStringDate(order1.getLastUpdateTime(),true));
            }
            pageBean.setDatas(list);
            return pageBean;
        }catch (Exception e){
            throw new OrderException("获取订单列表失败"+e.getMessage());
        }
    }


    @Override
    public Order getOrder(String orderId) {
        try {
            Order order1 = orderDao.getOrder(orderId);
            order1.setUserMc(userDao.getUser(order1.getUserId()).getUserMc());
            order1.setState(commonUtil.getOrderState(order1.getOrderState()));
            order1.setCreateTime(commonUtil.formatStringDate(order1.getCreateTime(),true));
            order1.setLastUpdateTime(commonUtil.formatStringDate(order1.getLastUpdateTime(),true));
            return order1;
        }catch (Exception e){
            throw new OrderException("获取订单信息失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void add(Order order) {
        try {
            if(null == order.getUserId() || null == order.getDemandId()){
                throw new OrderException("用户和需求不能为空");
            }
            if(!"1".equals(demandDao.getState(order.getDemandId()))){
                throw new OrderException("该需求已经被受理");
            }
            String date = commonUtil.dateToString(new Date());
            order.setCreateTime(date);
            order.setLastUpdateTime(date);
            Demand demand = demandDao.queryById(order.getDemandId());
            order.setCompanyId(demand.getCompanyId());
            order.setOrderTitle(demand.getDemandTitle());
            demand.setDemandState("2");
            demand.setLastUpdateTime(date);
            orderDao.add(order);
            demandDao.update(demand);
        }catch (Exception e){
            throw new OrderException("添加订单失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String orderId) {
        try {
            Order order = orderDao.getOrder(orderId);
            String demandId = order.getDemandId();
            if(!"2".equals(order.getOrderState()) && !"4".equals(order.getOrderState())){
                throw new OrderException("订单状态不允许删除");
            }
            if(!"3".equals(demandDao.getState(demandId))){
                Demand demand = new Demand();
                demand.setDemandId(demandId);
                demand.setDemandState("2");
                demandDao.update(demand);
            }

            orderDao.delete(orderId);
        }catch (Exception e){
            throw new OrderException("删除单个订单失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Order order) {
        try {
            order.setLastUpdateTime(commonUtil.dateToString(new Date()));
            orderDao.update(order);
        }catch (Exception e){
            throw new OrderException("更新订单信息失败!"+e.getMessage());
        }
    }

    @Override
    public int getCount(Order order) {
        try {
            return orderDao.getCount(order);
        }catch (Exception e){
            throw new OrderException("获取订单总数失败!"+e.getMessage());
        }
    }

    @Override
    public Demand getDemand(String orderId) {
        try {
            Demand demand = demandDao.queryById(orderDao.getOrder(orderId).getDemandId());
            demand.setTypeName(commonUtil.getDtypeName(demand.getDemandType()));
            demand.setCreateTime(commonUtil.formatStringDate(demand.getCreateTime(),true));
            demand.setLastUpdateTime(commonUtil.formatStringDate(demand.getLastUpdateTime(),true));
            return demand;
        }catch (Exception e){
            throw new OrderException("根据订单id获取需求失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateStar(Order order) {
        try {
            if(order.getUserId() == null || order.getOrderId() == null || order.getOrderScore() == null){
                throw new OrderException("参数错误");
            }
            Order order1 = orderDao.getOrder(order.getOrderId());
            if(null == order1){
                throw new OrderException("不存在该订单，无法评分");
            }
            if(!"2".equals(order1.getOrderState())){
                throw new OrderException("该订单未结束，无法评分");
            }
            if(null != order1.getOrderScore()){
                throw new OrderException("不能重复评分");
            }
            order.setOrderScore(order.getOrderScore());
            order.setLastUpdateTime(commonUtil.dateToString(new Date()));
            orderDao.update(order);

            User user = userDao.getUser(order.getUserId());
            float newScore = (float)Math.round(((user.getUserScore() + order.getOrderScore())/2)*10)/10;
            user.setUserId(order.getUserId());
            user.setUserScore(newScore);
            userDao.update(user);

        }catch (Exception e){
            throw new OrderException("订单评分失败"+e.getMessage());
        }

    }

    @Override
    public Order getLately(Order order) {
        try {
            if(order==null || order.getUserId() == null || order.getCompanyId() == null){
                throw new OrderException("参数错误");
            }
            order.setOrderState("2");
            Order order1 = orderDao.getLately(order);
            order1.setUserMc(userDao.getUser(order1.getUserId()).getUserMc());
            order1.setState(commonUtil.getOrderState(order1.getOrderState()));
            order1.setCreateTime(commonUtil.formatStringDate(order1.getCreateTime(),true));
            order1.setLastUpdateTime(commonUtil.formatStringDate(order1.getLastUpdateTime(),true));
            return order1;
        }catch (Exception e){
            throw new OrderException("获取最近用户订单失败！"+e.getMessage());
        }
    }
}
