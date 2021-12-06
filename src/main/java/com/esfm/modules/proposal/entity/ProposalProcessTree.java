package com.esfm.modules.proposal.entity;

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
 * 改善提案流程树图(ProposalProcessTree)表实体类
 * @author yaoxin
 * @since 2020-04-24 16:21:02
 */
@SuppressWarnings("serial")
@Data
public class ProposalProcessTree extends Model<ProposalProcessTree> {

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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新人登录名称", width = 20)

    private String updateBy;

    //更新日期
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //员工

    @Excel(name = "员工", width = 20)

    private String staffId;

    //部门

    @Excel(name = "部门", width = 20)

    private String dept;

    //权限许可

    @Excel(name = "权限许可", width = 20)

    private String permission;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
