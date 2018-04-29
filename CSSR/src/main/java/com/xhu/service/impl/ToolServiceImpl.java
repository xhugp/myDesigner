package com.xhu.service.impl;

import com.xhu.dao.ToolDao;
import com.xhu.dto.PageBean;
import com.xhu.entity.Tool;
import com.xhu.exception.ToolException;
import com.xhu.service.ToolService;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gp on 2018/3/22.
 */

@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private ToolDao toolDao;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public PageBean<Tool> list(Integer currentPage, Integer pageSize, Tool tool) {
        int count = getCount(tool);
        try {
            PageBean pageBean = new PageBean(pageSize,count,currentPage);
            List<Tool> list = toolDao.list(pageBean.getIndex(),pageBean.getSize(),tool);
            for (Tool tool1 : list){
                tool1.setTypeName(commonUtil.getTtypeName(tool1.getToolType()));
                tool1.setStateName(commonUtil.getToolStateName(tool1.getToolState()));
            }
            pageBean.setDatas(list);
            return pageBean;
        }catch (Exception e){
            throw new ToolException("获取工具列表失败!"+e.getMessage());
        }
    }

    @Override
    public Tool getTool(Integer toolId) {
        try {
            Tool tool = toolDao.getTool(toolId);
            tool.setTypeName(commonUtil.getTtypeName(tool.getToolType()));
            tool.setStateName(commonUtil.getToolStateName(tool.getToolState()));
            return tool;
        }catch (Exception e){
            throw new ToolException("获取工具信息失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void add(Tool tool) {
        try {
             toolDao.add(tool);
        }catch (Exception e){
            throw new ToolException("添加工具信息失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Tool tool) {
        try {
            toolDao.update(tool);
        }catch (Exception e){
            throw new ToolException("更新工具信息失败!"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Integer toolId) {
        try {
            toolDao.delete(toolId);
        }catch (Exception e){
            throw new ToolException("删除单个工具信息失败!"+e.getMessage());
        }
    }

    @Override
    public int getCount(Tool tool) {
        try {
            return toolDao.getCount(tool);
        }catch (Exception e){
            throw new ToolException("获取工具总数失败!"+e.getMessage());
        }

    }
}
