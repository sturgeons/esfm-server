package com.zf.zf_server.modules.lpa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 分层审核-审核表-子项(LpaChecklistItem)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:28:45
 */
@Data
public class LpaChecklistItem extends Model<LpaChecklistItem> {
    //序列号
    @Excel(name = "序列号", width = 20)
    private String id;
    //检查表id
    @Excel(name = "检查表id", width = 20)
    private String checklistId;
    //检查表名
    @Excel(name = "检查表名", width = 20)
    private String checklistName;
    //检查表序列号
    @Excel(name = "检查表序列号", width = 20)
    private Integer sortId;
    //检查点
    @Excel(name = "检查点", width = 20)
    private String point;
    //检查项
    @Excel(name = "检查项", width = 20)
    private String checklist;
    //分类
    @Excel(name = "分类", width = 20)
    private String category;
    //检查方法或指导
    @Excel(name = "检查方法或指导", width = 20)
    private String methods;
    //创建者
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建者", width = 20)
    private String createBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
