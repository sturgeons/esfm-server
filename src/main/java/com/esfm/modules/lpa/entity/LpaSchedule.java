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
 * 分层审核-审核计划(LpaSchedule)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:30:10
 */
@Data
public class LpaSchedule extends Model<LpaSchedule> {
    //编号
    @Excel(name = "编号", width = 20)
    private String id;
    //审核员姓名
    @Excel(name = "审核员姓名", width = 20)
    private String auditorName;
    //审核员编号
    @Excel(name = "审核员编号", width = 20)
    private String auditorId;
    //审核地点
    @Excel(name = "审核地点", width = 20)
    private String areaName;
    //区域编号
    @Excel(name = "区域编号", width = 20)
    private String areaId;
    //审核单名称
    @Excel(name = "审核单名称", width = 20)
    private String checkName;
    //检查单编号
    @Excel(name = "检查单编号", width = 20)
    private String checklistId;
    //计划时间
    @Excel(name = "计划时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;
    //完成时间
    @Excel(name = "完成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;
    //所用天数
    @Excel(name = "所用天数", width = 20)
    private Integer spendDays;
    //0-未完成 1-完成
    @Excel(name = "0-未完成 1-完成", width = 20)
    private Integer status;
    //备注
    @Excel(name = "备注", width = 20)
    private String commit;
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
