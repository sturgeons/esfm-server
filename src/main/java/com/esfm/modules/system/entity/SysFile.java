package com.esfm.modules.system.entity;

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
 * 文件存储(SysFile)表实体类
 *
 * @author yaoxin
 * @since 2021-11-30 14:38:20
 */
@Data
@SuppressWarnings("serial")
public class SysFile extends Model<SysFile> {
    //编号    
    @Excel(name = "编号", width = 20)
        private String id;
    //文件名    
    @Excel(name = "文件名", width = 20)
        private String filename;
    //md5    
    @Excel(name = "md5", width = 20)
        private String md5;
    //路径    
    @Excel(name = "路径", width = 20)
        private String url;
    //创建时间    
    @Excel(name = "创建时间", width = 20, exportFormat = "yyyy-MM-dd")
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
        private Date createTime;
    //更新时间    
    @Excel(name = "更新时间", width = 20, exportFormat = "yyyy-MM-dd")
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
        private Date updateTime;
    //创造者    
    @Excel(name = "创造者", width = 20)
    @TableField(fill = FieldFill.INSERT)
        private String createBy;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
    }