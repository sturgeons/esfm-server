package com.esfm.modules.lpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.lpa.dao.LpaChecklistDao;
import com.esfm.modules.lpa.entity.LpaAreaChecklist;
import com.esfm.modules.lpa.entity.LpaChecklist;
import com.esfm.modules.lpa.service.LpaAreaChecklistService;
import com.esfm.modules.lpa.service.LpaChecklistItemService;
import com.esfm.modules.lpa.service.LpaChecklistService;
import com.esfm.modules.lpa.entity.vo.ChecklistVo;
import com.esfm.modules.lpa.service.LpaScheduleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分层审核-审核表(LpaChecklist)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:28:31
 */
@Service("lpaChecklistService")
public class LpaChecklistServiceImpl extends ServiceImpl<LpaChecklistDao, LpaChecklist> implements LpaChecklistService {

    @Resource
    private LpaChecklistItemService lpaChecklistItemService;
    @Resource
    private LpaAreaChecklistService lpaAreaChecklistService;
    @Lazy
    @Resource
    private LpaScheduleService lpaScheduleService;
    @Override
    public R<?> andCheckItems(ChecklistVo checklistVo) {
      int res=  this.baseMapper.insert(checklistVo.getChecklist());
        checklistVo.getChecklistItems().forEach(t->{
            t.setChecklistId(checklistVo.getChecklist().getId());
            t.setChecklistName(checklistVo.getChecklist().getName());
        });
       var resItems= this.lpaChecklistItemService.saveBatch(checklistVo.getChecklistItems());
       if(res>0&&resItems) return R.ok(ResponseInfoConstant.OPERATE_SUCCESS);
       return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public R<?> listByArea(String area) {
        List<LpaChecklist> lpaChecklists=lpaAreaChecklistService.getChecklistByAreaDetail(area);
        return R.ok(lpaChecklists);
    }

    @Override
    public R<?> deleteByIds(List<String> idList) {

        var res= this.removeByIds(idList);
        idList.forEach(t->{
            //删除与审核单匹配的审核区域
            lpaAreaChecklistService.remove(new QueryWrapper<LpaAreaChecklist>().eq("checklist_id",t));
            //删除还未做的审核
            Map<String, Object> query=new HashMap<>();
            query.put("checklist_id",t);
            query.put("status",0);
            lpaScheduleService.removeByMap(query);
        });
        return res?R.ok(ResponseInfoConstant.OPERATE_SUCCESS):R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }
}
