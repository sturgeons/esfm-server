package com.esfm.modules.lpa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 分层审核-审核员(LpaAuditor)表实体类
 * @author yaoxin
 * @since 2020-08-21 11:08:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LpaAuditor extends Model<LpaAuditor> {
    private String id;
    //工号 索引
    @Excel(name = "工号 索引", width = 20)
    private String userId;
    //层级
    @Excel(name = "层级", width = 20)
    private Integer layer;
    //父级Id
    @Excel(name = "父级Id", width = 20)
    private String pid;
    //是否冻结
    @Excel(name = "是否冻结", width = 20)
    private Boolean freeze;
    //创造时间
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创造时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //删除时间
    @Excel(name = "删除时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;
    //冗余姓名
    @Excel(name = "冗余姓名", width = 20)
    private String userName;
    //创造着
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创造着", width = 20)
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
