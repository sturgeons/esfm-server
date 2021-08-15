package com.zf.zf_server.modules.lpa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.lpa.dao.LpaAreaChecklistDao;
import com.zf.zf_server.modules.lpa.entity.LpaAreaChecklist;
import com.zf.zf_server.modules.lpa.entity.LpaChecklist;
import com.zf.zf_server.modules.lpa.service.LpaAreaChecklistService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分层审核-区域和审核单匹配表(LpaAreaChecklist)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:27:59
 */
@Service("lpaAreaChecklistService")
public class LpaAreaChecklistServiceImpl extends ServiceImpl<LpaAreaChecklistDao, LpaAreaChecklist> implements LpaAreaChecklistService {

    @Override
    public List<LpaChecklist> getChecklistByAreaDetail(String area) {
      return   this.baseMapper.getChecklistByAreaDetail(area);
    }
}
