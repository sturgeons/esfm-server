package com.esfm.modules.workflow.entity;

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
 * 流程路线表(WorkflowNode)表实体类
 *
 * @author yaoxin
 * @since 2020-06-05 14:31:46
 */
@Data
@SuppressWarnings("serial")
public class WorkflowNode extends Model<WorkflowNode> {
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
  //流程编号
  @Excel(name="流程编号",width=20 )
    private String workflowId;
  //节点类型
  @Excel(name="节点类型",width=20 )
    private String type;
  //上级节点
  @Excel(name="上级节点",width=20 )
    private String prevnode;
  //结束节点
  @Excel(name="结束节点",width=20 )
    private String nextnode;
  //流向条件
  @Excel(name="流向条件",width=20 )
    private String flowCondition;
  //当前操作
  @Excel(name="当前操作",width=20 )
    private String handlers;
  //编辑权限
  @Excel(name="编辑权限",width=20 )
    private String cando;
  //节点编号
  @Excel(name="节点编号",width=20 )
    private String nodeNo;
  //名称
  @Excel(name="名称",width=20 )
    private String name;
  //行动
  @Excel(name="行动",width=20 )
    private String action;


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