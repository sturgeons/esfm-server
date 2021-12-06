package com.esfm.modules.gage.entity;

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
 * 检具台账(GageAccount)表实体类
 *
 * @author makejava
 * @since 2021-10-25 10:04:00
 */
@Data
@SuppressWarnings("serial")
public class GageAccount extends Model<GageAccount> {
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
    //检具名称    
    @Excel(name = "检具名称", width = 20)
    private String title;
    //制造方    
    @Excel(name = "制造方", width = 20)
    private String manufacturer;
    //校准周期    
    @Excel(name = "校准周期", width = 20)
    private String calibrationCycle;
    //最后校准日期    
    @Excel(name = "最后校准日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastCalibration;
    //下一次校准日期    
    @Excel(name = "下一次校准日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextCalibration;
    //存放位置    
    @Excel(name = "存放位置", width = 20)
    private String storageLocation;
    //当前存放位置    
    @Excel(name = "当前存放位置", width = 20)
    private String currentLocation;
    //最后借用人    
    @Excel(name = "最后借用人", width = 20)
    private String lastBorrow;
    //主照片    
    @Excel(name = "主照片", width = 20)
    private String mainPicture;
    //检具状态    
    @Excel(name = "检具状态", width = 20)
    private String gageStatus;
    //负责人    
    @Excel(name = "负责人", width = 20)
    private String responsible;
    //备注    
    @Excel(name = "备注", width = 20)
    private String context;
    //设计者    
    @Excel(name = "设计者", width = 20)
    private String designer;
    //测量范围    
    @Excel(name = "测量范围", width = 20)
    private String messurementRange;
    //校准机构    
    @Excel(name = "校准机构", width = 20)
    private String calibrationFacility;
    //证书编号    
    @Excel(name = "证书编号", width = 20)
    private String certificationNumber;
    //规格    
    @Excel(name = "规格", width = 20)
    private String type;
    //精度    
    @Excel(name = "精度", width = 20)
    private String gagePrecision;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
