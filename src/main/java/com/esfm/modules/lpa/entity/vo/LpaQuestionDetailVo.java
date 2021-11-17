package com.esfm.modules.lpa.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LpaQuestionDetailVo {
    @Excel(name = "编号", width = 20)
    private String id;
    @Excel(name = "计划序号", width = 20)
    private String scheduleId;
    @Excel(name = "审核条目", width = 40)
    private String checklist;
    @Excel(name = "发现项", width = 40)
    private String question;
    @Excel(name = "审核员", width = 20)
    private String auditorName;
    @Excel(name = "审核区域", width = 20)
    private String areaName;
    @Excel(name = "审核单", width = 20)
    private String checkName;
    @Excel(name = "审核单序号", width = 20)
    private String checklistItemId;
    @Excel(name = "审核单子序号", width = 20)
    private String sortId;
    @Excel(name = "审核单编号", width = 20)
    private String checklistId;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;
}
