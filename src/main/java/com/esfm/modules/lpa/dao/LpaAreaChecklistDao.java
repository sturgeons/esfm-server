package com.esfm.modules.lpa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esfm.modules.lpa.entity.LpaAreaChecklist;
import com.esfm.modules.lpa.entity.LpaChecklist;

import java.util.List;

/**
 * 分层审核-区域和审核单匹配表(LpaAreaChecklist)表数据库访问层
 * @author yaoxin
 * @since 2020-07-30 11:27:59
 */
public interface LpaAreaChecklistDao extends BaseMapper<LpaAreaChecklist> {

    List<LpaChecklist> getChecklistByAreaDetail(String area);
}
