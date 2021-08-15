package com.zf.zf_server.modules.dictionaries.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.dictionaries.entity.DicList;

/**
 * 字典清单(DicList)表服务接口
 * @author yaoxin
 * @since 2020-08-07 16:58:53
 */
public interface DicListService extends IService<DicList> {

    R<?> getMapByName(String name);

    R<?> getArray(String name);
}
