package com.xhu.dao;

import com.xhu.dto.PageBean;
import com.xhu.entity.Demand;
import com.xhu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by gp on 2018/3/20.
 */
public interface UserDao {
    public List<User> list(@Param("index") Integer index, @Param("size") Integer size, @Param("user") User user);

    public List<User> simple_list(Integer companyId);

    public int add(User user);

    public int update(User user);

    public int delete(Integer userId);

    public User getUser(Integer userId);

    public int getCount(User user);

    public User getUserByName(String userName);

    public User getUserByOpenId(String openId);

    public List<User> list_lately(HashMap<String,Object> map);

}
