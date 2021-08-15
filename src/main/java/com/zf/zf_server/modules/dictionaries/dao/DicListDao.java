package com.zf.zf_server.modules.dictionaries.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.zf_server.modules.dictionaries.entity.DicList;
import com.zf.zf_server.modules.dictionaries.entity.vo.SelectListVo;

import java.util.List;

/**
 * 字典清单(DicList)表数据库访问层
 * @author yaoxin
 * @since 2020-08-07 16:58:53
 */
public interface DicListDao extends BaseMapper<DicList> {

    // 用过表明获取键和值 的列表选项
    List<SelectListVo> getArrayFromTable(String tableName, String fieldName, String fieldValue);
}
