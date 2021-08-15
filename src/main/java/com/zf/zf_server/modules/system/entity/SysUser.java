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
 * 系统用户(SysUser)表实体类
 *
 * @author yaoxin
 * @since 2020-07-09 15:32:00
 */
@Data
@SuppressWarnings("serial")
public class SysUser extends Model<SysUser> {
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
  //验证码
  @Excel(name="验证码",width=20 )
    private String verificationCode;
  //工号
  @Excel(name="工号",width=20 )
    private String workNo;
  //用户名
  @Excel(name="用户名",width=20 )
    private String userName;
  //密码
  @Excel(name="密码",width=20 )
    private String password;
  //失效期限
  @Excel(name="失效期限",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expiryDate;
  //状态
  @Excel(name="状态",width=20 )
    private Integer status;
  //区域
  @Excel(name="区域",width=20 )
    private String area;
  //岗位
  @Excel(name="岗位",width=20 )
    private String position;
  //姓名
  @Excel(name="姓名",width=20 )
    private String name;
  //性别
  @Excel(name="性别",width=20 )
    private String sex;
  //部门
  @Excel(name="部门",width=20 )
    private String dept;
  //安全问题
  @Excel(name="安全问题",width=20 )
    private String question;
  //答案
  @Excel(name="答案",width=20 )
    private String answer;
  //照片
  @Excel(name="照片",width=20 )
    private String image;
  //电话
  @Excel(name="电话",width=20 )
    private String tel;
  //地址
  @Excel(name="地址",width=20 )
    private String address;
  //身份证号
  @Excel(name="身份证号",width=20 )
    private String code;
  //鞋子尺寸
  @Excel(name="鞋子尺寸",width=20 )
    private Double shoesSize;
  //衣服尺寸
  @Excel(name="衣服尺寸",width=20 )
    private String clothesSize;
  //在职
  @Excel(name="在职",width=20 )
    private Integer onJob;
  //人员类型
  @Excel(name="人员类型",width=20 )
    private String category;
  //微信序号
  @Excel(name="微信序号",width=20 )
    private String wxOpenid;
  //微信名
  @Excel(name="微信名",width=20 )
    private String wxNickname;
  //微信性别
  @Excel(name="微信性别",width=20 )
    private String wxSex;
  //微信语言
  @Excel(name="微信语言",width=20 )
    private String wxLanguage;
  //微信城市
  @Excel(name="微信城市",width=20 )
    private String wxCity;
  //微信省份
  @Excel(name="微信省份",width=20 )
    private String wxProvince;
  //微信国家
  @Excel(name="微信国家",width=20 )
    private String wxCountry;
  //微信头像
  @Excel(name="微信头像",width=20 )
    private String wxHeadImgUrl;
  //微信权限
  @Excel(name="微信权限",width=20 )
    private String wxPrivilege;
  //微信源
  @Excel(name="微信源",width=20 )
    private String wxResource;
  //审批标示
  @Excel(name="审批标示",width=20 )
    private String approveFlag;
  //审批备注
  @Excel(name="审批备注",width=20 )
    private String approveDesc;
  //排序序列
  @Excel(name="排序序列",width=20 )
    private Integer sortId;


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