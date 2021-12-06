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

/**
 * 生产数据-停机损失清单 (PdEscalationQrPicture)表实体类
 *
 * @author yaoxin
 * @since 2021-12-04 00:40:02
 */
@Data
@SuppressWarnings("serial")
public class PdEscalationQrPicture extends Model<PdEscalationQrPicture> {
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
    //qr的id    
    @Excel(name = "qr的id", width = 20)

    private String qrId;
    //文件id    
    @Excel(name = "文件id", width = 20)

    private String fileId;
    //文件分类    
    @Excel(name = "文件分类", width = 20)

    private String fileType;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}