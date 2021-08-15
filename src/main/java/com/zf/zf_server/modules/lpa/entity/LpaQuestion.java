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
 * 分层审核-审核问题(LpaQuestion)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:29:19
 */
@Data
public class LpaQuestion extends Model<LpaQuestion> {
    //编号
    @Excel(name = "编号", width = 20)
    private String id;
    //审核计划号
    @Excel(name = "审核计划号", width = 20)
    private String scheduleId;
    //问题描述
    @Excel(name = "问题描述", width = 20)
    private String question;
    //0-未解决 1-已解决
    @Excel(name = "0-未解决 1-已解决", width = 20)
    private Integer statue;
    //检查表id
    @Excel(name = "检查表id", width = 20)
    private String checklistId;
    //检查项id
    @Excel(name = "检查项id", width = 20)
    private String checklistItemId;
    //备注
    @Excel(name = "备注", width = 20)
    private String commit;
    //责任人
    @Excel(name = "责任人", width = 20)
    private String owner;
    //关闭时间
    @Excel(name = "关闭时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;
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
