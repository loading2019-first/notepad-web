package com.llg.modules.notepad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.PageUtils;
import com.llg.modules.notepad.entity.CustomerInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 小程序用户信息表
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 08:35:40
 */
public interface CustomerInfoService extends IService<CustomerInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);


    CustomerInfoEntity queryWxCustomerInfoById(long id);


    CustomerInfoEntity queryWxCustomerInfo(CustomerInfoEntity query);

    List<CustomerInfoEntity> queryByName(String name);




    void createWxCustomerInfo(CustomerInfoEntity wxCustomerInfo);


    int modifyWxCustomerInfo(CustomerInfoEntity wxCustomerInfo);

    CustomerInfoEntity findById(long id) ;

    List<CustomerInfoEntity> queryListByPage(CustomerInfoEntity query);

    int countSelective(CustomerInfoEntity query);

    List<CustomerInfoEntity> queryWxCustomerInfoList(CustomerInfoEntity query);

    void add(CustomerInfoEntity wxCustomerInfo);

    boolean updateById(CustomerInfoEntity wxCustomerInfo);

    int deleteById(long id);
}

