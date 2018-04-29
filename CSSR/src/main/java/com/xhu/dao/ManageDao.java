package com.xhu.dao;

import com.xhu.dto.PageBean;
import com.xhu.entity.Manage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gp on 2018/3/8.
 */
public interface ManageDao {
    public List<Manage> list(@Param("index") Integer index,@Param("size") Integer size);

    public Manage queryById(Integer manageId);

    public Manage queryByName(String manageName);

    public List<Manage> queryByCompany(@Param("companyId") Integer companyId, @Param("pageBean") PageBean pageBean);

    public int add(Manage manage);

    public int update(Manage manage);

    public int deleteById(Integer manageId);

    public int deleteBatch(List<String> deleteList);

    public int getCount(Integer companyId);

}
