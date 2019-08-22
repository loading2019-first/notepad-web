package com.llg.modules.notepad.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.Query;

import com.llg.modules.notepad.dao.ExhibitionApplyDao;
import com.llg.modules.notepad.entity.ExhibitionApplyEntity;
import com.llg.modules.notepad.service.ExhibitionApplyService;


@Service("exhibitionApplyService")
public class ExhibitionApplyServiceImpl extends ServiceImpl<ExhibitionApplyDao, ExhibitionApplyEntity> implements ExhibitionApplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExhibitionApplyEntity> page = this.page(
                new Query<ExhibitionApplyEntity>().getPage(params),
                new QueryWrapper<ExhibitionApplyEntity>()
        );

        return new PageUtils(page);
    }

}