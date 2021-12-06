package com.esfm.modules.productionCenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.productionCenter.dao.PdLostDicDao;
import com.esfm.modules.productionCenter.entity.PdLostDic;
import com.esfm.modules.productionCenter.service.PdLostDicService;
import org.springframework.stereotype.Service;

/**
 * 生产数据-停机损失清单 (PdLostDic)表服务实现类
 *
 * @author yaoxin
 * @since 2021-11-18 16:42:26
 */
@Service("pdLostDicService")
public class PdLostDicServiceImpl extends ServiceImpl<PdLostDicDao, PdLostDic> implements PdLostDicService {

}