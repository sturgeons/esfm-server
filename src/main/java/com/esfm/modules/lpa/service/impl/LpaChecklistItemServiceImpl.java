package com.esfm.modules.lpa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.lpa.dao.LpaChecklistItemDao;
import com.esfm.modules.lpa.entity.LpaChecklistItem;
import com.esfm.modules.lpa.service.LpaChecklistItemService;
import org.springframework.stereotype.Service;

/**
 * 分层审核-审核表-子项(LpaChecklistItem)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:28:46
 */
@Service("lpaChecklistItemService")
public class LpaChecklistItemServiceImpl extends ServiceImpl<LpaChecklistItemDao, LpaChecklistItem> implements LpaChecklistItemService {

}
