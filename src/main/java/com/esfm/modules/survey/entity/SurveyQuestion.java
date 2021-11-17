package com.esfm.modules.survey.entity;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 问卷调查问题表(SurveyQuestion)表实体类
 *
 * @author makejava
 * @since 2021-10-31 22:57:58
 */
@Data
@SuppressWarnings("serial")
public class SurveyQuestion extends Model<SurveyQuestion> {
    //主键    
    @Excel(name = "主键", width = 20)
    private String id;
    //创建人登录名称    
    @Excel(name = "创建人登录名称", width = 20)
    private String createBy;
    //创建日期    
    @Excel(name = "创建日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人登录名称    
    @Excel(name = "更新人登录名称", width = 20)
    private String updateBy;
    //更新日期    
    @Excel(name = "更新日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //问卷id    
    @Excel(name = "问卷id", width = 20)
    private String surveyId;
    //问题流水号    
    @Excel(name = "问题流水号", width = 20)
    private Integer sortNum;
    //题目类型    
    @Excel(name = "题目类型", width = 20)
    private String questionType;
    //答案    
    @Excel(name = "答案", width = 20)
    private String answer;
    //积分    
    @Excel(name = "积分", width = 20)
    private Integer coin;
    //问题模板    
    @Excel(name = "问题模板", width = 20)
    private String questionTemplate;
    //题干    
    @Excel(name = "题干", width = 20)
    private String context;
    //辅助信息    
    @Excel(name = "辅助信息", width = 20)
    private String subContext;
    //合格介绍图片    
    @Excel(name = "合格介绍图片", width = 20)
    private String pictureOk;
    //不合格介绍图片    
    @Excel(name = "不合格介绍图片", width = 20)
    private String pictureNok;
    @Excel(name = "显示", width = 20)
    private Boolean visible;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
