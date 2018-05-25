package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Oreq;
import com.xhu.entity.Recruit;
import com.xhu.service.RecruitService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/5/22.
 */
@RestController
@RequestMapping(value = "/api")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @RequestMapping(value = "/recruit/add",method = RequestMethod.POST)
    private ResultBean add(@RequestBody Recruit recruit){
        recruitService.add(recruit);
        return new ResultBean("200","添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "/recruit/list",method = RequestMethod.POST)
    public ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("recruit"));
        Recruit recruit = (Recruit) JSONObject.toBean(json,Recruit.class);
        System.out.println(recruit);
        PageBean pageBean = recruitService.list(currentPage,pageSize,recruit);
        return new ResultBean("200",pageBean,"需求列表");
    }

    @RequestMapping(value = "/recruit/delete/{recruitId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable("recruitId") Integer recruitId){
        recruitService.deleteById(recruitId);
        return new ResultBean("200","删除招聘信息成功");
    }

}
