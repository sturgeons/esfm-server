package com.esfm.modules.gage.entity;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 检具台账附件(GageAccountFile)表实体类
 *
 * @author makejava
 * @since 2021-10-24 21:18:28
 */
@Data
@SuppressWarnings("serial")
public class GageAccountFile extends Model<GageAccountFile> {
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
    //检具id    
    @Excel(name = "检具id", width = 20)
    private String gageId;
    //链接    
    @Excel(name = "链接", width = 20)
    private String url;
    //显示文本    
    @Excel(name = "显示文本", width = 20)
    private String text;
    //类别    
    @Excel(name = "类别", width = 20)
    private String type;
    //备注    
    @Excel(name = "备注", width = 20)
    private String context;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
