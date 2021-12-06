package com.esfm.modules.proposal.entity.vo;

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
import java.util.Map;

/**
 * 改善提案(Proposal)表实体类
 *
 * @author yaoxin
 * @since 2020-04-24 16:48:49
 */
@SuppressWarnings("serial")
@Data
public class ProposalVo extends Model<ProposalVo> {

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

    //提案人

    @Excel(name="提案人",width=20 )


    private String staffId;

    //主题

    @Excel(name="主题",width=20 )


    private String title;

    //问题点

    @Excel(name="问题点",width=20 )


    private String issues;

    //想法

    @Excel(name="想法",width=20 )


    private String idea;

    //提出时间

    @Excel(name="提出时间",width=20 ,format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date proposeTime;

    //状态

    @Excel(name="状态",width=20 )


    private String state;

    //分类

    @Excel(name="分类",width=20 )


    private String category;

    //是否采纳

    @Excel(name="是否采纳",width=20 )


    private String adoptFlag;

    //是否完成

    @Excel(name="是否完成",width=20 )


    private String completeFlag;

    //评审分数

    @Excel(name="评审分数",width=20 )


    private Integer evaluationScore;

    //评审等级

    @Excel(name="评审等级",width=20 )


    private Integer evaluationLevel;

    //当前状态流程号

    @Excel(name="当前状态流程号",width=20 )


    private Integer currentProcessNo;

    //部门

    @Excel(name="部门",width=20 )


    private String dept;

    //员工姓名

    @Excel(name="员工姓名",width=20 )
    private String staffName;

    //图片列表显示
    //{ name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }
    @TableField(exist = false)
    private List<Map<String,String>> pictures;
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
