package com.xhu.dao;

import com.xhu.entity.Tool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/21.
 */
public interface ToolDao {
    public List<Tool> list(@Param("index")Integer index,@Param("size")Integer size,@Param("tool")Tool tool);

    public Tool getTool(Integer toolId);

    public int add(Tool tool);

    public int update(Tool tool);

    public int delete(Integer toolId);

    public int getCount(Tool tool);
}
