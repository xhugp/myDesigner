package com.xhu.web;

import com.sun.org.apache.regexp.internal.RE;
import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Demand;
import com.xhu.entity.LReq;
import com.xhu.service.LReqService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/3/30.
 */
@RestController
@RequestMapping(value = "/api")
public class LReqController {
    @Autowired
    private LReqService lReqService;

    @RequestMapping(value = "/lreq/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("lReq"));
        LReq lReq = (LReq) JSONObject.toBean(json,LReq.class);
        PageBean pageBean = lReqService.list(currentPage,pageSize,lReq);
        return new ResultBean("200",pageBean,"请求列表");
    }

    @RequestMapping(value = "/lreq/handle",method = RequestMethod.POST)
    private ResultBean handle(@RequestBody LReq lReq){
        lReqService.updateResult(lReq,false);
        return new ResultBean("200",null,"请求处理成功");
    }

    @RequestMapping(value = "/lreq/handle/over",method = RequestMethod.POST)
    private ResultBean handleover(@RequestBody LReq lReq){
        lReqService.updateResult(lReq,true);
        return new ResultBean("200",null,"请求处理成功");
    }

    @RequestMapping(value = "/lreq/{lReqId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable("lReqId") Integer lReqId){
        lReqService.delete(lReqId);
        return new ResultBean("200",null,"删除请求记录成功");
    }

    @RequestMapping(value = "/lreq/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody LReq lReq){
        lReqService.add(lReq);
        return new ResultBean("200",null,"请求结果");
    }
}
