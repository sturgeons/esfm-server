package com.esfm.modules.salary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.salary.entity.SalaryTemplateItem;

import java.util.List;

/**
 * 人员历史薪资模板子类(SalaryTemplateItem)表服务接口
 *
 * @author makejava
 * @since 2021-06-01 19:41:21
 */
public interface SalaryTemplateItemService extends IService<SalaryTemplateItem> {

    R<?> getItemByTemplateId(String templateId);

    R<?> saveSalaryItems(List<SalaryTemplateItem> items);
}