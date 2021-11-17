package com.esfm.modules.gage.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.modules.gage.dao.GageAccountDao;
import com.esfm.modules.gage.entity.GageAccount;
import com.esfm.modules.gage.service.GageAccountService;
import org.springframework.stereotype.Service;

/**
 * 检具台账(GageAccount)表服务实现类
 *
 * @author makejava
 * @since 2021-10-24 21:17:53
 */
@Service("gageAccountService")
public class GageAccountServiceImpl extends ServiceImpl<GageAccountDao, GageAccount> implements GageAccountService {

    @Override
    public R<?> needCalibrationInMonthList(Page<GageAccount> page) {
        Page<GageAccount> gageAccounts = getBaseMapper().selectPage(page, new QueryWrapper<GageAccount>().apply("next_calibration<= date_format('" + DateUtil.offsetDay(DateTime.now(), 30).toDateStr() + "','%Y-%m-%d')"));

        return R.ok(gageAccounts);
    }
}
