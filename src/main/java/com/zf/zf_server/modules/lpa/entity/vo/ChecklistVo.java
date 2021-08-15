package com.zf.zf_server.modules.lpa.entity.vo;

import com.zf.zf_server.modules.lpa.entity.LpaChecklist;
import com.zf.zf_server.modules.lpa.entity.LpaChecklistItem;
import lombok.Data;

import java.util.List;

@Data
public class ChecklistVo {
    private LpaChecklist checklist;
    private List<LpaChecklistItem> checklistItems;
}
