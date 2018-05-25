package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Demand;
import com.xhu.service.DemandService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by gp on 2018/3/15.
 */
@RestController
@RequestMapping("/api")
public class DemandController {
    @Autowired
    private DemandService demandService;

    @RequestMapping(value = "/demand/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("demand"));
        Demand demand = (Demand) JSONObject.toBean(json,Demand.class);
        PageBean pageBean = demandService.list(currentPage,pageSize,demand);
        return new ResultBean("200",pageBean,"需求列表");
    }

    @RequestMapping(value = "/demand/demand-user",method = RequestMethod.POST)
    private ResultBean demandUser(@RequestBody Map<String,Object> reqMap){
        String demandId = (String) reqMap.get("demandId");
        Integer userId = (Integer) reqMap.get("userId");

        return null;
    }

    @RequestMapping(value = "/demand/get/{demandId}",method = RequestMethod.GET)
    private ResultBean get(@PathVariable String demandId){
        return  new ResultBean("200",demandService.queryById(demandId),"单个需求信息");
    }

    @RequestMapping(value = "/demand/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Demand demand){
        demandService.add(demand);
        return new ResultBean("200","添加成功");
    }

    @RequestMapping(value = "demand/update",method = RequestMethod.POST)
    private ResultBean update(@RequestBody Demand demand){
        demandService.update(demand);
        return new ResultBean("200","修改成功");
    }

    @RequestMapping(value = "demand/reupdate",method = RequestMethod.POST)
    private ResultBean reUpdate(@RequestBody Demand demand){
        demandService.reUpdate(demand);
        return new ResultBean("200","重提成功");
    }

    @RequestMapping(value = "demand/delete/{demandId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable("demandId") String demandId){
        demandService.deleteById(demandId);
        return new ResultBean("200","删除单个成功");
    }

    @RequestMapping(value = "demand/delete-batch/{demandIds}",method = RequestMethod.DELETE)
    private ResultBean deleteBatch(@PathVariable String demandIds){
        demandService.deleteBatch(demandIds);
        return new ResultBean("200","批量删除成功");
    }

}
