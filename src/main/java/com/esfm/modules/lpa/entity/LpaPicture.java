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
 * 分层审核-图片(LpaPicture)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:29:02
 */
@Data
public class LpaPicture extends Model<LpaPicture> {
    //编号
    @Excel(name = "编号", width = 20)
    private String id;
    //计划编号
    @Excel(name = "计划编号", width = 20)
    private String scheduleId;
    //检查表编号
    @Excel(name = "检查表编号", width = 20)
    private String checklistId;
    //路径
    @Excel(name = "路径", width = 20)
    private String url;
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
    //创造者
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创造者", width = 20)
    private String createBy;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
