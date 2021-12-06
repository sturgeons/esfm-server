package com.esfm.modules.system.entity;

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
 * 系统日志表(SysLog)表实体类
 *
 * @author yaoxin
 * @since 2020-05-27 15:14:53
 */
@Data
public class SysLog extends Model<SysLog> {
    private String id;
    //日志类型（1登录日志，2操作日志）
    @Excel(name = "日志类型（1登录日志，2操作日志）", width = 20)
    private Integer logType;
    //日志内容
    @Excel(name = "日志内容", width = 20)
    private String logContent;
    //操作类型
    @Excel(name = "操作类型", width = 20)
    private Integer operateType;
    //操作用户账号
    @Excel(name = "操作用户账号", width = 20)
    private String userid;
    //操作用户名称
    @Excel(name = "操作用户名称", width = 20)
    private String username;
    //IP
    @Excel(name = "IP", width = 20)
    private String ip;
    //请求java方法
    @Excel(name = "请求java方法", width = 20)
    private String method;
    //请求路径
    @Excel(name = "请求路径", width = 20)
    private String requestUrl;
    //请求参数
    @Excel(name = "请求参数", width = 20)
    private String requestParam;
    //请求类型
    @Excel(name = "请求类型", width = 20)
    private String requestType;
    //耗时
    @Excel(name = "耗时", width = 20)
    private Integer costTime;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建人", width = 20)
    private String createBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新人", width = 20)
    private String updateBy;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}