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
 * 字典清单(DicList)表实体类
 * @author yaoxin
 * @since 2020-08-07 16:58:53
 */
@Data
public class DicList extends Model<DicList> {
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
    //名称
    @Excel(name = "名称", width = 20)
    private String name;
    //类型
    @Excel(name = "类型", width = 20)
    private String type;
    //表名
    @Excel(name = "表名", width = 20)
    private String tableName;
    //注
    @Excel(name = "注", width = 20)
    private String context;
    //列名
    @Excel(name = "列名", width = 20)
    private String fieldName;
    //列值
    @Excel(name = "列值", width = 20)
    private String fieldValue;
    //条件
    @Excel(name = "条件", width = 20)
    private String conditionValue;
    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
