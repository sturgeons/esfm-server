package com.zf.zf_server.modules.lpa.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 分层审核-审核区域(LpaArea)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:26:35
 */
@Data
public class LpaArea extends Model<LpaArea> {
    //序列号
    @Excel(name = "序列号", width = 20)
    private String id;
    //审核区域名
    @Excel(name = "审核区域名", width = 20)
    private String area;
    //分类
    @Excel(name = "分类", width = 20)
    private String catefory;
    //0-无效 1-有效
    @Excel(name = "0-无效 1-有效", width = 20)
    private Integer active;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //创建者
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建者", width = 20)
    private String createBy;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
