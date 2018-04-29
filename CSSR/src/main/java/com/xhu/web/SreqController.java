package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Sreq;
import com.xhu.service.SreqService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/4/23.
 */
@RestController
@RequestMapping("/api")
public class SreqController {
    @Autowired
    private SreqService sreqService;

    @RequestMapping(value = "/sreq/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("sreq"));
        Sreq sreq = (Sreq) JSONObject.toBean(json,Sreq.class);
        PageBean pageBean = sreqService.list(currentPage,pageSize,sreq);
        return new ResultBean("200",pageBean,"请求列表");
    }

    @RequestMapping(value = "/sreq/{srId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable Integer srId){
        sreqService.delete(srId);
        return new ResultBean("200","删除成功");
    }

    @RequestMapping(value = "/sreq/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Sreq sreq){
        sreqService.add(sreq);
        return new ResultBean("200","添加成功");
    }

    @RequestMapping(value = "/sreq/handle",method = RequestMethod.POST)
    private ResultBean handle(@RequestBody Sreq sreq){
        sreqService.update(sreq);
        return new ResultBean("200","处理成功");
    }

    @RequestMapping(value = "/sreq/{srId}",method = RequestMethod.GET)
    private ResultBean get(@PathVariable Integer srId){
        return new ResultBean("200",sreqService.getLReq(srId),"查看请求");
    }
}
