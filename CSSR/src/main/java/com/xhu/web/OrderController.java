package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Order;
import com.xhu.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/3/29.
 */
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("order"));
        Order order = (Order) JSONObject.toBean(json,Order.class);
        PageBean pageBean = orderService.list(currentPage,pageSize,order);
        return new ResultBean("200",pageBean,"订单列表");
    }

    @RequestMapping(value = "/order/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Order order){
        orderService.add(order);
        return new ResultBean("200",null,"添加订单成功");
    }

    @RequestMapping(value = "/order/get-demand/{orderId}",method = RequestMethod.GET)
    private ResultBean getDemand(@PathVariable("orderId") String orderId){
        return new ResultBean("200",orderService.getDemand(orderId),"订单对应需求信息");
    }

    @RequestMapping(value = "/order/{orderId}",method = RequestMethod.GET)
    private ResultBean getOrder(@PathVariable("orderId") String orderId){
        return new ResultBean("200",orderService.getOrder(orderId),"订单信息");
    }

    @RequestMapping(value = "/order/by-demand/{demandId}",method = RequestMethod.GET)
    private ResultBean getOrderByDemand(@PathVariable("demandId") String demandId){
        return new ResultBean("200",orderService.getOrderByDemand(demandId),"需求对应的订单信息");
    }

    @RequestMapping(value = "/order/delete",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable String orderId){
        orderService.delete(orderId);
        return new ResultBean("200","删除成功");
    }

    @RequestMapping(value = "/order/lately",method = RequestMethod.POST)
    private ResultBean lately(@RequestBody Order order){
        return new ResultBean("200",orderService.getLately(order),"最近订单");
    }

    @RequestMapping(value = "/order/star",method = RequestMethod.POST)
    private ResultBean star(@RequestBody Order order){
        orderService.updateStar(order);
        return new ResultBean("200","评分成功");
    }
}
