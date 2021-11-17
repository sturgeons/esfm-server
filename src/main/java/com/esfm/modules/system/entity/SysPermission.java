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
 * 权限页面(SysPermission)表实体类
 *
 * @author yaoxin
 * @since 2020-05-27 14:51:43
 */
@Data
@SuppressWarnings("serial")
public class SysPermission extends Model<SysPermission> {
  //主键
  @Excel(name="主键",width=20 )
    private String id;
  //创建人登录名称
   @TableField(fill = FieldFill.INSERT)
  @Excel(name="创建人登录名称",width=20 )
    private String createBy;
  //创建日期
   @TableField(fill = FieldFill.INSERT)
  @Excel(name="创建日期",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
  //更新人登录名称
 @TableField(fill = FieldFill.UPDATE)
  @Excel(name="更新人登录名称",width=20 )
    private String updateBy;
  //更新日期
   @TableField(fill = FieldFill.INSERT_UPDATE)
  @Excel(name="更新日期",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
  //父级
  @Excel(name="父级",width=20 )
    private String parentId;
  //名称
  @Excel(name="名称",width=20 )
    private String title;
  //是否是路由
  @Excel(name="是否是路由",width=20 )
    private String isRoute;
  //图标
  @Excel(name="图标",width=20 )
    private String icon;
  //路径
  @Excel(name="路径",width=20 )
    private String path;
  //排序编号
  @Excel(name="排序编号",width=20 )
    private Integer sortNo;
  //状态
  @Excel(name="状态",width=20 )
    private Integer status;


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