package com.esfm.modules.lpa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esfm.modules.lpa.entity.LpaAuditor;
import com.esfm.modules.lpa.entity.vo.LpaPerformanceVo;

import java.util.List;

/**
 * 分层审核-审核员(LpaAuditor)表数据库访问层
 * @author yaoxin
 * @since 2020-07-30 11:28:16
 */
public interface LpaAuditorDao extends BaseMapper<LpaAuditor> {
    List<LpaPerformanceVo> performance();

}
