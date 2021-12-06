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

/**
 * 生产数据详情表(PdDtl)表实体类
 *
 * @author yaoxin
 * @since 2021-11-18 16:34:23
 */
@Data
@SuppressWarnings("serial")
public class PdDtl extends Model<PdDtl> {
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
    //周数    
    @Excel(name = "周数", width = 20)
    private Integer countWeek;
    //记录日期    
    @Excel(name = "记录日期", width = 20)
    private Object recordDate;
    //班次    
    @Excel(name = "班次", width = 20)
    private String shift;
    //产线名称    
    @Excel(name = "产线名称", width = 20)
    private String lineName;
    //产线id    
    @Excel(name = "产线id", width = 20)
    private String lineId;
    //停机损失时间分钟    
    @Excel(name = "停机损失时间分钟", width = 20)
    private Integer lostA;
    //质量损失时间分钟    
    @Excel(name = "质量损失时间分钟", width = 20)
    private Integer lostQ;
    //速度损失时间分钟    
    @Excel(name = "速度损失时间分钟", width = 20)
    private Integer lostP;
    //开线时长    
    @Excel(name = "开线时长", width = 20)
    private Integer lineDuration;
    //开线时间    
    @Excel(name = "开线时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lineStart;
    //停线时间    
    @Excel(name = "停线时间", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lineEnd;
    //产出时长    
    @Excel(name = "产出时长", width = 20)
    private Integer outputDuration;
    //投入时长    
    @Excel(name = "投入时长", width = 20)
    private Integer inputDuration;
    //效率     
    @Excel(name = "效率 ", width = 20)
    private Object pe;
    //设备综合利用率    
    @Excel(name = "设备综合利用率", width = 20)
    private Object oee;
    //停机损失    
    @Excel(name = "停机损失", width = 20)
    private Object a;
    //效率损失    
    @Excel(name = "效率损失", width = 20)
    private Object p;
    //质量损失    
    @Excel(name = "质量损失", width = 20)
    private Object q;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}