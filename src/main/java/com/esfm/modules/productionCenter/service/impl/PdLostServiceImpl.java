package com.esfm.modules.productionCenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.productionCenter.dao.PdLostDao;
import com.esfm.modules.productionCenter.entity.PdLost;
import com.esfm.modules.productionCenter.service.PdLostService;
import org.springframework.stereotype.Service;

/**
 * 生产数据-停机损失清单 (PdLost)表服务实现类
 *
 * @author yaoxin
 * @since 2021-11-18 16:41:46
 */
@Service("pdLostService")
public class PdLostServiceImpl extends ServiceImpl<PdLostDao, PdLost> implements PdLostService {

}