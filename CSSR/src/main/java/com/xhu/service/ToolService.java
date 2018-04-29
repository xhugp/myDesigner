package com.xhu.service;

import com.xhu.dto.PageBean;
import com.xhu.entity.Tool;

/**
 * Created by gp on 2018/3/22.
 */
public interface ToolService {
    public PageBean<Tool> list(Integer currentPage, Integer pageSize,Tool tool);

    public Tool getTool(Integer toolId);

    public void add(Tool tool);

    public void update(Tool tool);

    public void delete(Integer toolId);

    public int getCount(Tool tool);
}
