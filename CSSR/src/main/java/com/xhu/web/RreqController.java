package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Rreq;
import com.xhu.service.RreqService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/5/22.
 */
@RestController
@RequestMapping("/api")
public class RreqController {
    @Autowired
    private RreqService rreqService;

    @RequestMapping(value = "/rreq/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("rreq"));
        Rreq rreq = (Rreq) JSONObject.toBean(json,Rreq.class);
        PageBean pageBean = rreqService.list(currentPage,pageSize,rreq);
        return new ResultBean("200",pageBean,"请求列表");
    }

    @RequestMapping(value = "/rreq/{rreqId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable("rreqId") Integer rreqId){
        rreqService.delete(rreqId);
        return new ResultBean("200","删除成功");
    }

    @RequestMapping(value = "/rreq/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Rreq rreq){
        rreqService.add(rreq);
        return new ResultBean("200","添加成功");
    }

    @RequestMapping(value = "/rreq/update",method = RequestMethod.POST)
    private ResultBean update(@RequestBody Rreq rreq){
        System.out.println(rreq);
        rreqService.update(rreq);
        return new ResultBean("200","添加成功");
    }

    @RequestMapping(value = "/rreq/{rreqId}",method = RequestMethod.GET)
    private ResultBean get(@PathVariable("rreqId") Integer rreqId){
        return new ResultBean("200",rreqService.getRreq(rreqId),"查看请求");
    }
}
