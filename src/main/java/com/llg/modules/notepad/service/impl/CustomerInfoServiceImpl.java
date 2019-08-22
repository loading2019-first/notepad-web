package com.llg.modules.notepad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.Query;
import com.llg.modules.notepad.dao.CustomerInfoDao;
import com.llg.modules.notepad.entity.CustomerInfoEntity;
import com.llg.modules.notepad.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("customerInfoService")
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoDao, CustomerInfoEntity> implements CustomerInfoService {
    @Autowired
    CustomerInfoDao customerInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerInfoEntity> page = this.page(
                new Query<CustomerInfoEntity>().getPage(params),
                new QueryWrapper<CustomerInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CustomerInfoEntity queryWxCustomerInfoById(long id) {

        CustomerInfoEntity wxCustomerInfo = this.findById(id);
        return wxCustomerInfo;
    }

    @Override
    public CustomerInfoEntity queryWxCustomerInfo(CustomerInfoEntity query) {

        CustomerInfoEntity wxCustomerInfo = this.baseMapper.selectOne(new QueryWrapper<CustomerInfoEntity>().setEntity(query));
        return wxCustomerInfo;
    }

    @Override
    public List<CustomerInfoEntity> queryByName(String name) {
        CustomerInfoEntity query = new CustomerInfoEntity();
        query.setUsername(name);

        List<CustomerInfoEntity> list = this.baseMapper.selectList(new QueryWrapper<CustomerInfoEntity>().setEntity(query));
        return list;
    }

    @Override
    public void createWxCustomerInfo(CustomerInfoEntity wxCustomerInfo) {

        this.baseMapper.insert(wxCustomerInfo);
    }

    @Override
    public int modifyWxCustomerInfo(CustomerInfoEntity wxCustomerInfo) {

        return this.baseMapper.updateById(wxCustomerInfo);
    }

    @Override
    public CustomerInfoEntity findById(long id) {

        CustomerInfoEntity wxCustomerInfo = this.baseMapper.selectById(id);
        return wxCustomerInfo;
    }

    @Override
    public List<CustomerInfoEntity> queryListByPage(CustomerInfoEntity query) {
        IPage<CustomerInfoEntity> page = this.page(
                new Query<CustomerInfoEntity>().getPage(null),
                new QueryWrapper<CustomerInfoEntity>()
        );
        return page.getRecords();
    }
    @Override
    public int countSelective(CustomerInfoEntity query) {

        return 10;
    }

    @Override
    public List<CustomerInfoEntity> queryWxCustomerInfoList(CustomerInfoEntity query) {

        List<CustomerInfoEntity> list = this.baseMapper.selectList(new QueryWrapper<CustomerInfoEntity>().setEntity(query));
        return list;
    }

    @Override
    public void add(CustomerInfoEntity wxCustomerInfo) {

        this.baseMapper.insert(wxCustomerInfo);
    }


    @Override
    public boolean updateById(CustomerInfoEntity wxCustomerInfo) {

        this.baseMapper.updateById(wxCustomerInfo);
        return true;
    }

    @Override
    public int deleteById(long id) {

        int num = this.baseMapper.deleteById(id);
        return num;
    }

}