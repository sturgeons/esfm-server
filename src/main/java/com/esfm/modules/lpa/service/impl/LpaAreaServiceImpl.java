package com.esfm.modules.lpa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.lpa.dao.LpaAreaDao;
import com.esfm.modules.lpa.entity.LpaArea;
import com.esfm.modules.lpa.service.LpaAreaService;
import org.springframework.stereotype.Service;

/**
 * 分层审核-审核区域(LpaArea)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:26:38
 */
@Service("lpaAreaService")
public class LpaAreaServiceImpl extends ServiceImpl<LpaAreaDao, LpaArea> implements LpaAreaService {

}
