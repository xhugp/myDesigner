package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Oreq;
import com.xhu.service.OreqService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/4/4.
 */
@RestController
@RequestMapping(value = "/api")
public class OreqController {
    @Autowired
    private OreqService oreqService;

    @RequestMapping(value = "/oreq/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("oreq"));
        Oreq oreq = (Oreq) JSONObject.toBean(json,Oreq.class);
        PageBean pageBean = oreqService.list(currentPage,pageSize,oreq);
        return new ResultBean("200",pageBean,"订单请求列表");
    }

    @RequestMapping(value = "/oreq/handle/over",method = RequestMethod.POST)
    private ResultBean handleover(@RequestBody Oreq oreq){
        oreqService.updateResult(oreq,true);
        return new ResultBean("200",null,"请求处理成功");
    }

    @RequestMapping(value = "/oreq/handle",method = RequestMethod.POST)
    private ResultBean handle(@RequestBody Oreq oreq){
        oreqService.updateResult(oreq,false);
        return new ResultBean("200",null,"请求处理成功");
    }

    @RequestMapping(value = "/oreq/{oreqId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable("oreqId") Integer oreqId){
        oreqService.delete(oreqId);
        return new ResultBean("200",null,"删除请求记录成功");
    }

    @RequestMapping(value = "/oreq/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Oreq oreq){
        oreqService.add(oreq);
        return new ResultBean("200",null,"请求结果");
    }
}
