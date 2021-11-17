package com.esfm.modules.lpa.entity.vo;

import com.esfm.modules.lpa.entity.LpaChecklist;
import com.esfm.modules.lpa.entity.LpaChecklistItem;
import lombok.Data;

import java.util.List;

@Data
public class ChecklistVo {
    private LpaChecklist checklist;
    private List<LpaChecklistItem> checklistItems;
}
