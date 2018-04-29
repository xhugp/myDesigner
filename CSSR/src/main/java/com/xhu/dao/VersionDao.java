package com.xhu.dao;

import com.xhu.entity.Version;

/**
 * Created by gp on 2018/3/21.
 */
public interface VersionDao {
    public Version getVersion();

    public int add(Version version);
}
