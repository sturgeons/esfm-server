package com.zf.zf_server.modules.lpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.modules.lpa.entity.LpaAreaChecklist;
import com.zf.zf_server.modules.lpa.entity.LpaChecklist;

import java.util.List;

/**
 * 分层审核-区域和审核单匹配表(LpaAreaChecklist)表服务接口
 * @author yaoxin
 * @since 2020-07-30 11:27:59
 */
public interface LpaAreaChecklistService extends IService<LpaAreaChecklist> {

    List<LpaChecklist> getChecklistByAreaDetail(String area);
}
