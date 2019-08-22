/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.sys.service.impl;

import com.llg.common.common.CommonConstant;
import com.llg.common.utils.Constant;
import com.llg.common.utils.RedisUtils;
import com.llg.modules.sys.dao.SysMenuDao;
import com.llg.modules.sys.dao.SysUserDao;
import com.llg.modules.sys.dao.SysUserTokenDao;
import com.llg.modules.sys.entity.SysMenuEntity;
import com.llg.modules.sys.entity.SysUserEntity;
import com.llg.modules.sys.entity.SysUserTokenEntity;
import com.llg.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;
    @Autowired
    private RedisUtils util;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        String key = CommonConstant.TOKEN_FLAG+token;
        SysUserTokenEntity userToken = util.get(key,SysUserTokenEntity.class);//tokenMap.get(token);
        if (userToken==null){
            //redis没有从数据库获取
            userToken = sysUserTokenDao.queryByToken(token);
        }
        return   userToken;
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
