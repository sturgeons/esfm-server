package com.esfm.modules.gage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.gage.dao.GageTransferLogDao;
import com.esfm.modules.gage.entity.GageTransferLog;
import com.esfm.modules.gage.service.GageTransferLogService;
import org.springframework.stereotype.Service;

/**
 * 转库记录(GageTransferLog)表服务实现类
 *
 * @author makejava
 * @since 2021-10-24 21:19:26
 */
@Service("gageTransferLogService")
public class GageTransferLogServiceImpl extends ServiceImpl<GageTransferLogDao, GageTransferLog> implements GageTransferLogService {

}
