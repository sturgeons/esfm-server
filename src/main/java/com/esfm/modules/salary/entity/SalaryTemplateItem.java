package com.esfm.modules.salary.entity;

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
 * 人员历史薪资模板子类(SalaryTempateItem)表实体类
 *
 * @author makejava
 * @since 2021-06-01 19:41:21
 */
@Data
public class SalaryTemplateItem extends Model<SalaryTemplateItem> {
    //主键    
    @Excel(name = "主键", width = 20)
    private String id;
    //创建人登录名称    
    @Excel(name = "创建人登录名称", width = 20)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    //创建日期    
    @Excel(name = "创建日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新人登录名称    
    @Excel(name = "更新人登录名称", width = 20)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    //更新日期    
    @Excel(name = "更新日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //模板主键    
    @Excel(name = "模板主键", width = 20)
    private String templateId;
    //名字    
    @Excel(name = "名字", width = 20)
    private String columnsName;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}