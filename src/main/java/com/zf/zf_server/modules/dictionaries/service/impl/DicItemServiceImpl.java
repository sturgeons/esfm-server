package com.zf.zf_server.modules.dictionaries.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.dictionaries.dao.DicItemDao;
import com.zf.zf_server.modules.dictionaries.entity.DicItem;
import com.zf.zf_server.modules.dictionaries.service.DicItemService;
import org.springframework.stereotype.Service;

/**
 * 字典子类(DicItem)表服务实现类
 * @author yaoxin
 * @since 2020-08-07 16:59:14
 */
@Service("dicItemService")
public class DicItemServiceImpl extends ServiceImpl<DicItemDao, DicItem> implements DicItemService {

}
