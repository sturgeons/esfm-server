package com.zf.zf_server.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单表(SysMenu)表实体类
 * @author yaoxin
 * @since 2020-08-17 17:20:48
 */
@Data
@SuppressWarnings("serial")
public class SysMenu extends Model<SysMenu> {
    //主键
    @Excel(name = "主键", width = 20)
    private String id;
    //创建人登录名称
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建人登录名称", width = 20)
    private String createBy;
    //创建日期
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人登录名称
    @TableField(fill = FieldFill.UPDATE)
    @Excel(name = "更新人登录名称", width = 20)
    private String updateBy;
    //更新日期
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //路径
    @Excel(name = "路径", width = 20)
    private String path;
    //菜单名称
    @Excel(name = "菜单名称", width = 20)
    private String title;
    //图标
    @Excel(name = "图标", width = 20)
    private String icon;
    //父菜单ID，一级菜单设为0
    @Excel(name = "父菜单ID，一级菜单设为0", width = 20)
    private String parentId;
    //层级：1级、2级、3级......
    @Excel(name = "层级：1级、2级、3级......", width = 20)
    private String grade;
    //排序
    @Excel(name = "排序", width = 20)
    private Integer sortNum;
    //类型：0 目录；1 菜单；2 按钮
    @Excel(name = "类型：0 目录；1 菜单；2 按钮", width = 20)
    private String type;
    //授权(多个用逗号分隔，如：sys:menu:list,sys:menu:create)
    @Excel(name = "授权(多个用逗号分隔，如：sys:menu:list,sys:menu:create)", width = 20)
    private String permission;
    //描述
    @Excel(name = "描述", width = 20)
    private String descr;
    //可见性
    @Excel(name = "可见性", width = 20)
    private Boolean visibility;
    //接口端
    @Excel(name = "接口端", width = 20)
    private String terminal;
    @TableField(exist = false)
    private List<SysMenu> children;


    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
