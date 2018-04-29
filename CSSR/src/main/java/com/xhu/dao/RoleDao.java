package com.xhu.dao;

import com.xhu.entity.Role;

import java.util.List;

/**
 * Created by gp on 2018/3/22.
 */
public interface RoleDao {
    public List<Role> list();

    public int add(Role role);

    public int delete(int id);
}
