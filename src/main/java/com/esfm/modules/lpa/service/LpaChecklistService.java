package com.esfm.modules.lpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.entity.LpaChecklist;
import com.esfm.modules.lpa.entity.vo.ChecklistVo;

import java.util.List;

/**
 * 分层审核-审核表(LpaChecklist)表服务接口
 * @author yaoxin
 * @since 2020-07-30 11:28:30
 */
public interface LpaChecklistService extends IService<LpaChecklist> {

    R<?> andCheckItems(ChecklistVo checklistVo);

    R<?> listByArea(String area);

    R<?> deleteByIds(List<String> idList);
}
