package com.esfm.modules.deliveryCheck.entity;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 发货检验(DeliveryCheck)表实体类
 *
 * @author makejava
 * @since 2021-09-29 17:17:11
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
    //barcode条码信息    
    @Excel(name = "barcode条码信息", width = 20)
    private String barcodeData;
    //barcode流水号    
    @Excel(name = "barcode流水号", width = 20)
    private String barcodeSn;
    //客户零件号    
    @Excel(name = "客户零件号", width = 20)
    private String customerPartNum;
    //客户标签照片    
    @Excel(name = "客户标签照片", width = 20)
    private String customerLabelPictureId;
    //产品标签照片    
    @Excel(name = "产品标签照片", width = 20)
    private String partLabelPictureId;
    //检查日期    
    @Excel(name = "检查日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
