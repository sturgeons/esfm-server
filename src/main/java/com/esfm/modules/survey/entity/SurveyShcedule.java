package com.esfm.modules.survey.entity;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 问卷调查计划(SurveyShcedule)表实体类
 *
 * @author makejava
 * @since 2021-11-02 10:10:45
 */
@Data
@SuppressWarnings("serial")
public class SurveyShcedule extends Model<SurveyShcedule> {
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
    private String surveryListId;
    //地点    
    @Excel(name = "地点", width = 20)
    private String location;
    //地点id    
    @Excel(name = "地点id", width = 20)
    private String locationId;
    //用户    
    @Excel(name = "用户", width = 20)
    private String user;
    //用户id    
    @Excel(name = "用户id", width = 20)
    private String userId;
    //计划状态 1-计划中 2-已完成 0-中止    
    @Excel(name = "计划状态 1-计划中 2-已完成 0-中止", width = 20)
    private Integer scheduleState;
    //计划时间    
    @Excel(name = "计划时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;
    //完成时间    
    @Excel(name = "完成时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completeTime;
    //问卷目的    
    @Excel(name = "问卷目的", width = 20)
    private String intent;
    //问卷类型    
    @Excel(name = "问卷类型", width = 20)
    private String intentType;
    //奖励积分    
    @Excel(name = "奖励积分", width = 20)
    private Integer awardScore;
    //奖励类型    
    @Excel(name = "奖励类型", width = 20)
    private String awardType;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
