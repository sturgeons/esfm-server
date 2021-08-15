package com.zf.zf_server.modules.lpa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.zf_server.modules.lpa.entity.LpaSchedule;

import java.util.List;

/**
 * 分层审核-审核计划(LpaSchedule)表数据库访问层
 * @author yaoxin
 * @since 2020-07-30 11:30:11
 */
public interface LpaScheduleDao extends BaseMapper<LpaSchedule> {

    List<LpaSchedule> month(String month);
}
