package com.esfm.modules.survey.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 问卷调查选项(SurveyOptions)表实体类
 *
 * @author makejava
 * @since 2021-10-31 22:49:29
 */
@Data
public class SurveyOptions extends Model<SurveyOptions> {
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
    //问题id    
    @Excel(name = "问题id", width = 20)
    private String questionId;
    //排序    
    @Excel(name = "排序", width = 20)
    private Integer sortNum;
    //选项键    
    @Excel(name = "选项键", width = 20)
    private String optionKey;
    //类型    
    @Excel(name = "类型", width = 20)
    private String optionType;
    //图片链接    
    @Excel(name = "图片链接", width = 20)
    private String url;
    //选项内容    
    @Excel(name = "选项内容", width = 20)
    private String context;
    //逻辑    
    @Excel(name = "逻辑", width = 20)
    private String logic;
    //正则校验    
    @Excel(name = "正则校验", width = 20)
    private String answerRegexp;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
