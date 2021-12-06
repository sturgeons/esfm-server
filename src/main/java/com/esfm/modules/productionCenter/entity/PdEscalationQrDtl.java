package com.esfm.modules.productionCenter.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 升级汇报流程-快速响应(PdEscalationQrDtl)表实体类
 *
 * @author yaoxin
 * @since 2021-12-04 00:08:37
 */
@Data
@SuppressWarnings("serial")
public class PdEscalationQrDtl extends Model<PdEscalationQrDtl> {
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
    //什么问题    
    @Excel(name = "什么问题", width = 20)
    private String what;
    //发现时间    
    @Excel(name = "发现时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qrWhen;
    //发现人    
    @Excel(name = "发现人", width = 20)
    private String who;
    //发现地点    
    @Excel(name = "发现地点", width = 20)
    private String qrWhere;
    //为什么    
    @Excel(name = "为什么", width = 20)
    private String why;
    //怎么分辨    
    @Excel(name = "怎么分辨", width = 20)
    private String how;
    //数量    
    @Excel(name = "数量", width = 20)
    private String howMany;
    //三十频率    
    @Excel(name = "三十频率", width = 20)
    private Integer frequency;
    //等级    
    @Excel(name = "等级", width = 20)
    private String qrLevel;
    //分类    
    @Excel(name = "分类", width = 20)
    private String type;
    //围堵时间    
    @Excel(name = "围堵时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date containDateTime;
    //围堵方法    
    @Excel(name = "围堵方法", width = 20)
    private String containMethod;
    //状态标识    
    @Excel(name = "状态标识", width = 20)
    private String stateFlag;

    @TableField(exist = false)
    private List<String> pictures;
    @TableField(exist = false)
    private List<String> containPictures;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}