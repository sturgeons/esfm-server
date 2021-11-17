package com.esfm.modules.lpa.entity;

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
 * 分层审核-审核表(LpaChecklist)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:28:30
 */
@Data
@SuppressWarnings("serial")
public class LpaChecklist extends Model<LpaChecklist> {
    //序列号
    @Excel(name = "序列号", width = 20)
    private String id;
    //审核单类型
    @Excel(name = "审核单类型", width = 20)
    private String auditClass;
    //审核表名称
    @Excel(name = "审核表名称", width = 20)
    private String name;
    //状态 0-无效 1-有效
    @Excel(name = "状态 0-无效 1-有效", width = 20)
    private Integer active;
    //作者
    @Excel(name = "作者", width = 20)
    private String editor;
    //上级审核表
    @Excel(name = "上级审核表", width = 20)
    private Integer pid;
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
