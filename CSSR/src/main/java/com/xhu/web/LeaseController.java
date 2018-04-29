package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Lease;
import com.xhu.service.LeaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/3/29.
 */
@RestController
@RequestMapping(value = "/api")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;

    @RequestMapping(value = "/lease/get-by-tool/{toolId}",method = RequestMethod.GET)
    private ResultBean getLeaseByTool(@PathVariable("toolId") Integer toolId){
        return new ResultBean("200",leaseService.getLeaseByTool(toolId),"根据工具查询租赁信息");
    }

    @RequestMapping(value = "/lease/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("lease"));
        Lease lease = (Lease) JSONObject.toBean(json,Lease.class);
        PageBean pageBean = leaseService.list(currentPage,pageSize,lease);
        return new ResultBean("200",pageBean,"需求列表");
    }
}
