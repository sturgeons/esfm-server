package com.zf.zf_server.modules.deliveryCheck.entity;

import java.util.Date;
import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Vqe主积分表
(DeliveryCheck)表实体类
 *
 * @author makejava
 * @since 2021-09-01 14:55:41
 */
@Data
@SuppressWarnings("serial")
public class DeliveryCheck extends Model<DeliveryCheck> {
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
    //客户标签信息    
    @Excel(name = "客户标签信息", width = 20)
        private String customerStr;
    //物流标签信息    
    @Excel(name = "物流标签信息", width = 20)
        private String barcodeStr;
    //产品标签信息    
    @Excel(name = "产品标签信息", width = 20)
        private String partStr;
    //客户零件号    
    @Excel(name = "客户零件号", width = 20)
        private String partNumCustomer;
    //采埃孚零件号    
    @Excel(name = "采埃孚零件号", width = 20)
        private String partNumZf;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
    }