package com.zf.zf_server.modules.salary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.salary.entity.SalaryHistory;

import java.util.List;

/**
 * 人员历史薪资(SalaryHistory)表服务接口
 *
 * @author makejava
 * @since 2021-06-01 19:40:39
 */
public interface SalaryHistoryService extends IService<SalaryHistory> {

    R<?> upload(List<SalaryHistory> SalaryHistorys);
    //获取当前用户的历史薪资
    R<?> getHistorys(String token);
}