package com.llg.modules.sys.service;


import com.llg.common.common.CommonConstant;
import com.llg.common.utils.CharUtil;
import com.llg.common.utils.DateUtil;
import com.llg.common.utils.RedisUtils;
import com.llg.modules.sys.entity.SysUserTokenEntity;
import com.llg.modules.sys.entity.UserToken;
import com.llg.modules.sys.oauth2.TokenGenerator;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class UserTokenManager {
    @Autowired
    private SysUserTokenService tokenService;

    @PostConstruct
    public void init(){
        sysUserTokenService = tokenService;
    }

    private static  SysUserTokenService sysUserTokenService;
    
    public static Long getUserId(String token,RedisUtils util) {

    	String key = CommonConstant.TOKEN_FLAG+token;
        SysUserTokenEntity userToken = util.get(key,SysUserTokenEntity.class);//tokenMap.get(token);
        if(userToken == null){
            return null;
        }

//        if(userToken.getExpireTime().compareTo(DateUtil.getCurrentDateTime())>0){
//            //tokenMap.remove(token);
//        	util.delete(key);
//            //idMap.remove(userToken.getUserId());
//        	util.delete(CommonConstant.LOGIN_ID+userToken.getUserId());
//            return null;
//        }

        return userToken.getUserId();
    }


    public static SysUserTokenEntity generateToken(Long id,RedisUtils util){


        SysUserTokenEntity userToken = null;

//        String token = CharUtil.getRandomString(32);
//        if (StringUtil.isEmpty((String) util.get(token))) {
//            token = CharUtil.getRandomString(32);
//        }

        String token = TokenGenerator.generateValue();
        userToken = sysUserTokenService.getById(id);
        if (userToken==null){
            userToken = new SysUserTokenEntity();
            userToken.setToken(token);
            userToken.setUpdateTime(DateUtil.getCurrentDateTime(DateUtil.fullDate));
            userToken.setExpireTime(DateUtil.addDateDay(new Date(), CommonConstant.WX_LOGIN_OUT_DAYS));
            userToken.setUserId(id);
            sysUserTokenService.save(userToken);
        }else {
            userToken.setUpdateTime(DateUtil.getCurrentDateTime(DateUtil.fullDate));
            userToken.setExpireTime(DateUtil.addDateDay(new Date(), CommonConstant.WX_LOGIN_OUT_DAYS));
            sysUserTokenService.updateById(userToken);
        }

        util.set(CommonConstant.TOKEN_FLAG+userToken.getToken(), userToken,CommonConstant.WX_LOGIN_OUT_TIME);
        util.set(CommonConstant.LOGIN_ID+id, userToken,CommonConstant.WX_LOGIN_OUT_TIME);

        //tokenMap.put(token, userToken);
        //idMap.put(id, userToken);

        return userToken;
    }
}
