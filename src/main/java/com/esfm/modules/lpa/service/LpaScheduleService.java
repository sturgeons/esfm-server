package com.esfm.modules.lpa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.entity.LpaSchedule;

/**
 * 分层审核-审核计划(LpaSchedule)表服务接口
 * @author yaoxin
 * @since 2020-07-30 11:30:11
 */
public interface LpaScheduleService extends IService<LpaSchedule> {

    R<?> reAudit(String id);

    R<?> month(String month);

    R<?> newSchedule(LpaSchedule lpaSchedule);

    R<?> getScheduleByUser(Page<LpaSchedule> page, String token, String status);

    R<?> close(LpaSchedule lpaSchedule);

    R<?> noProduction(LpaSchedule lpaSchedule);

    R<?> getCloseListByUser(Page<LpaSchedule> page, String token);
}
