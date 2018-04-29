package com.xhu.dao;

import com.xhu.entity.Order;
import com.xhu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/20.
 */
public interface OrderDao {
    public List<Order> list(@Param("index") Integer index, @Param("size") Integer size, @Param("order") Order order);

    public Order getOrder(String orderId);

    public int add(Order order);

    public int delete(String orderId);

    public int update(Order order);

    public int getCount(Order order);

    public List<Integer> userId_list(Order order);

    public Order getLately(Order order);

}
