package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.Tool;
import com.xhu.service.ToolService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/3/27.
 */
@RestController
@RequestMapping("/api")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @ResponseBody
    @RequestMapping(value = "/tool/list",method = RequestMethod.POST)
    public ResultBean getTools(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("tool"));
        Tool tool = (Tool) JSONObject.toBean(json,Tool.class);
        PageBean pageBean = toolService.list(currentPage,pageSize,tool);
        return new ResultBean("200",pageBean,"需求列表");
    }
    @ResponseBody
    @RequestMapping(value = "/tool/delete/{toolId}",method = RequestMethod.DELETE)
    public ResultBean delete(@PathVariable Integer toolId){
        toolService.delete(toolId);
        return new ResultBean("200",null,"需求列表");
    }

    @ResponseBody
    @RequestMapping(value = "/tool/add",method = RequestMethod.POST)
    public ResultBean add(@RequestBody Tool tool){
        toolService.add(tool);
        return new ResultBean("200","上传成功");
    }

    @ResponseBody
    @RequestMapping(value = "/tool/{toolId}",method = RequestMethod.GET)
    public ResultBean getTool(@PathVariable Integer toolId){
        return new ResultBean("200",toolService.getTool(toolId),"工具信息");
    }

    @ResponseBody
    @RequestMapping(value = "tool/img-url/{toolId}",method = RequestMethod.GET)
    public ResultBean getImgURl(@PathVariable Integer toolId){
        String imgUrl = toolService.getTool(toolId).getToolImg();
        return new ResultBean("200",imgUrl,"图片路径");
    }
}
