package com.esfm.modules.dictionaries.entity;

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
 * 字典子类(DicItem)表实体类
 * @author yaoxin
 * @since 2020-08-07 16:59:12
 */
@Data
public class DicItem extends Model<DicItem> {
    //主键
    @Excel(name = "主键", width = 20)
    private String id;
    //创建人登录名称
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建人登录名称", width = 20)
    private String createBy;
    //创建日期
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人登录名称
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新人登录名称", width = 20)
    private String updateBy;
    //更新日期
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //字典id
    @Excel(name = "字典id", width = 20)
    private String dicListId;
    //选择排序
    @Excel(name = "选择排序", width = 20)
    private Integer optionSort;
    //备注信息
    @Excel(name = "备注信息", width = 20)
    private String context;
    //显示信息
    @Excel(name = "显示信息", width = 20)
    private String optionText;
    //选项值
    @Excel(name = "选项值", width = 20)
    private String optionValue;
    //选项分类
    @Excel(name = "选项分类", width = 20)
    private String optionIndex;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
