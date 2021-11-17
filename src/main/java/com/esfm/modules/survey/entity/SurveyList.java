package com.esfm.modules.survey.entity;


import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 问卷调查问卷表(SurveyList)表实体类
 *
 * @author makejava
 * @since 2021-10-31 18:43:29
 */
@Data
public class SurveyList extends Model<SurveyList> {
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
    //标题    
    @Excel(name = "标题", width = 20)
    private String title;
    //显示问题编号    
    @Excel(name = "显示问题编号", width = 20)
    private Boolean showQuestionNumber;
    //显示进度条    
    @Excel(name = "显示进度条", width = 20)
    private Boolean showProcessBar;
    //需要验证码    
    @Excel(name = "需要验证码", width = 20)
    private Boolean needVerificationCode;
    //传播类型    
    @Excel(name = "传播类型", width = 20)
    private Integer publishType;
    //是否有效    
    @Excel(name = "是否有效", width = 20)
    private Boolean enableState;
    //有效类型    
    @Excel(name = "有效类型", width = 20)
    private String enableType;
    //开始时间    
    @Excel(name = "开始时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    //结束时间    
    @Excel(name = "结束时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    //有效份数    
    @Excel(name = "有效份数", width = 20)
    private Integer enableCount;
    //备注    
    @Excel(name = "备注", width = 20)
    private String remark;
    //几次显示答案    
    @Excel(name = "几次显示答案", width = 20)
    private Integer showAnswerCount;
    //大于提交    
    @Excel(name = "大于提交", width = 20)
    private Integer gtScoreSubmit;
    //大于提交
    @Excel(name = "答题类型", width = 20)
    private Integer paperType;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
