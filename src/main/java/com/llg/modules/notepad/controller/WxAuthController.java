package com.llg.modules.notepad.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSONObject;
import com.llg.common.utils.*;
import com.llg.modules.notepad.entity.CustomerInfoEntity;
import com.llg.modules.notepad.service.CustomerInfoService;
import com.llg.modules.sys.entity.SysUserTokenEntity;
import com.llg.modules.sys.entity.UserInfo;
import com.llg.modules.sys.entity.UserToken;
import com.llg.modules.sys.entity.WxLoginInfo;
import com.llg.modules.sys.service.SysUserTokenService;
import com.llg.modules.sys.service.UserTokenManager;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {
	private static Logger log = LoggerFactory.getLogger(WxAuthController.class);

    @Autowired
    private CustomerInfoService userService;

    @Autowired
    private WxMaService wxService;
    
    @Autowired
    private RedisUtils util;


    /**
     * 账号登录
     *
     * @param body 请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      errno: 0,
     *      errmsg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @RequestMapping("login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        if(username == null || password == null){
            return ResponseUtil.badArgument();
        }

        List<CustomerInfoEntity> userList =userService.queryByName(username);
        CustomerInfoEntity user = null;
        if(userList.size() > 1){
            return ResponseUtil.fail502();
        }
        else if(userList.size() == 0){
            return ResponseUtil.badArgumentValue();
        }
        else {
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, user.getPassword())){
            return ResponseUtil.fail(403, "账号密码不对");
        }

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        SysUserTokenEntity userToken = UserTokenManager.generateToken(user.getId(),util);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }

    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      errno: 0,
     *      errmsg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @RequestMapping("login_by_weixin")
    public Object loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        log.info("=======login_by_weixin===========");
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        if(code == null || userInfo == null){
            return ResponseUtil.badArgument();
        }
        log.info("code={},userinfo={}",code,userInfo);

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        if(sessionKey == null || openId == null){
            return ResponseUtil.fail();
        }
        CustomerInfoEntity info = new CustomerInfoEntity();
        info.setWeixinOpenid(openId);
        CustomerInfoEntity user = userService.queryWxCustomerInfo(info);
        if(user == null){
            user = new CustomerInfoEntity();
            user.setUsername(userInfo.getNickName());  // 其实没有用，因为用户没有真正注册
            user.setPassword(openId);                  // 其实没有用，因为用户没有真正注册
            user.setWeixinOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setGender(userInfo.getGender() == 1 ? "男" : "女");
            user.setUserLevel("普通用户");
            user.setStatus("可用");
            user.setProvince(userInfo.getProvince());
            user.setCity(userInfo.getCity());
            user.setLastLoginTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
            user.setLastLoginIp(IpUtil.client(request));
            userService.createWxCustomerInfo(user);
        }
        else{
            user.setLastLoginTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
            user.setLastLoginIp(IpUtil.client(request));
            userService.modifyWxCustomerInfo(user);
        }

        // token
        SysUserTokenEntity userToken = UserTokenManager.generateToken(user.getId(),util);
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        log.info("return value ={}",JSONObject.toJSONString(result));
        return ResponseUtil.ok(result);
    }

    /**
     * 账号注册
     *
     * @param body 请求内容
     *  {
     *      username: xxx,
     *      password: xxx,
     *      mobile: xxx
     *      code: xxx
     *  }
     *  其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      errno: 0,
     *      errmsg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("register")
    public Object register(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");
        String code = JacksonUtil.parseString(body, "code");

        if(username == null || password == null || mobile == null || code == null){
            return ResponseUtil.badArgument();
        }

        List<CustomerInfoEntity> userList = userService.queryByName(username);
        if(userList.size() > 0){
            return ResponseUtil.fail(403, "用户名已注册");
        }
        CustomerInfoEntity info = new CustomerInfoEntity();
        info.setWeixinOpenid(mobile);
        userList = userService.queryWxCustomerInfoList(info);
        if(userList.size() > 0){
            return ResponseUtil.fail(403, "手机号已注册");
        }
        if(!RegexUtil.isMobileExact(mobile)){
            return ResponseUtil.fail(403, "手机号格式不正确");
        }
        CustomerInfoEntity user = new CustomerInfoEntity();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        user = new CustomerInfoEntity();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setMobile(mobile);
        user.setWeixinOpenid("");
        user.setAvatar("https://yanxuan.nosdn.127.net/80841d741d7fa3073e0ae27bf487339f.jpg?imageView&quality=90&thumbnail=64x64");
        user.setNickname(username);
        user.setGender("未知");
        user.setUserLevel("普通用户");
        user.setStatus("可用");
        user.setLastLoginTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
        user.setLastLoginIp(IpUtil.client(request));
        userService.createWxCustomerInfo(user);


        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        SysUserTokenEntity userToken = UserTokenManager.generateToken(user.getId(),util);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }

    /**
     * 账号密码重置
     *
     * @param body 请求内容
     *  {
     *      password: xxx,
     *      mobile: xxx
     *      code: xxx
     *  }
     *  其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     *   成功则 { errno: 0, errmsg: '成功' }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("reset")
    public Object reset(@RequestBody String body, HttpServletRequest request) {
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");
        String code = JacksonUtil.parseString(body, "code");

        if(mobile == null || code == null || password == null){
            return ResponseUtil.badArgument();
        }
        CustomerInfoEntity info = new CustomerInfoEntity();
        info.setWeixinOpenid(mobile);
        List<CustomerInfoEntity> userList = userService.queryWxCustomerInfoList(info);
        CustomerInfoEntity user = null;
        if(userList.size() > 1){
            return ResponseUtil.serious();
        }
        else if(userList.size() == 0){
            return ResponseUtil.fail(403, "手机号未注册");
        }
        else{
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        userService.modifyWxCustomerInfo(user);

        return ResponseUtil.ok();
    }
}
