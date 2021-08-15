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

/**
 * 用户对应的角色表(SysUserRole)表实体类
 * @author yaoxin
 * @since 2020-07-20 13:05:31
 */
@Data
@SuppressWarnings("serial")
public class SysUserRole extends Model<SysUserRole> {
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
    //用户id
    @Excel(name = "用户id", width = 20)
    private String userId;
    //角色id
    @Excel(name = "角色id", width = 20)
    private String roleId;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
