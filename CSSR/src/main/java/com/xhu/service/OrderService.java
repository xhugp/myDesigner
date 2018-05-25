package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Demand;
import com.xhu.entity.Order;
import com.xhu.entity.User;

import java.util.List;

/**
 * Created by gp on 2018/3/22.
 */
public interface OrderService {
    public PageBean<Order> list(Integer currentPage, Integer pageSize, Order order);

    public Order getOrder(String orderId);

    public Order getOrderByDemand(String demandId);

    public void add(Order order);

    public void delete(String orderId);

    public void update(Order order);

    public void updateStar(Order order);

    public int getCount(Order order);

    public Demand getDemand(String orderId);

    public Order getLately(Order order);
}
