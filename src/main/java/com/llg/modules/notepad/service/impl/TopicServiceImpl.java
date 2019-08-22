package com.llg.modules.notepad.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.Query;

import com.llg.modules.notepad.dao.TopicDao;
import com.llg.modules.notepad.entity.TopicEntity;
import com.llg.modules.notepad.service.TopicService;


@Service("topicService")
public class TopicServiceImpl extends ServiceImpl<TopicDao, TopicEntity> implements TopicService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TopicEntity> page = this.page(
                new Query<TopicEntity>().getPage(params),
                new QueryWrapper<TopicEntity>()
        );

        return new PageUtils(page);
    }

}