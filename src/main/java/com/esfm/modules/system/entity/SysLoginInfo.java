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
 * 登录历史(SysLoginInfo)表实体类
 *
 * @author yaoxin
 * @since 2020-07-12 13:54:03
 */
@Data
public class SysLoginInfo extends Model<SysLoginInfo> {
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
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @Excel(name="更新人登录名称",width=20 )
    private String updateBy;
  //更新日期
   @TableField(fill = FieldFill.INSERT_UPDATE)
  @Excel(name="更新日期",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
  //员工编号
  @Excel(name="员工编号",width=20 )
    private String userId;
  //地址
  @Excel(name="地址",width=20 )
    private String ip;
  //登录期限
  @Excel(name="登录期限",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginDate;
  //状态
  @Excel(name="状态",width=20 )
    private Integer status;
  //密钥
  @Excel(name="密钥",width=20 )
    private String token;
  //失效时间
  @Excel(name="失效时间",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expiryTime;


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
