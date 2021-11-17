package com.esfm.modules.gage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.gage.entity.GageAccount;

/**
 * 检具台账(GageAccount)表服务接口
 *
 * @author makejava
 * @since 2021-10-24 21:17:52
 */
public interface GageAccountService extends IService<GageAccount> {

    R<?> needCalibrationInMonthList(Page<GageAccount> page);
}
