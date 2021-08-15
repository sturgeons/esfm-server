package com.zf.zf_server.modules.proposal.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 改善提案反馈(ProposalCallback)表实体类
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:46
 */
@SuppressWarnings("serial")
public class ProposalCallback extends Model<ProposalCallback> {

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

    //提案id    
                
    @Excel(name="提案id",width=20 )    
        
        
    private String proposalId;

    //反馈内容    
                
    @Excel(name="反馈内容",width=20 )    
        
        
    private String context;

    //反馈人员    
                
    @Excel(name="反馈人员",width=20 )    
        
        
    private String callbackName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

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