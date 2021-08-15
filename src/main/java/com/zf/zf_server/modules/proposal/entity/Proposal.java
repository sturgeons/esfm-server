package com.zf.zf_server.modules.proposal.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 改善提案(Proposal)表实体类
 * @author yaoxin
 * @since 2020-07-13 20:57:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("serial")
public class Proposal extends Model<Proposal> {
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
    //提案人
    @Excel(name = "提案人", width = 20)
    private String staffId;
    //主题
    @Excel(name = "主题", width = 20)
    private String title;
    //问题点
    @Excel(name = "问题点", width = 20)
    private String issues;
    //想法
    @Excel(name = "想法", width = 20)
    private String idea;
    //提出时间
    @Excel(name = "提出时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date proposeTime;
    //状态
    @Excel(name = "状态", width = 20)
    private String state;
    //分类
    @Excel(name = "分类", width = 20)
    private String category;
    //是否采纳
    @Excel(name = "是否采纳", width = 20)
    private String adoptFlag;
    //是否完成
    @Excel(name = "是否完成", width = 20)
    private String completeFlag;
    //评审分数
    @Excel(name = "评审分数", width = 20)
    private Double evaluationScore;
    //评审等级
    @Excel(name = "评审等级", width = 20)
    private Integer evaluationLevel;
    //当前状态流程号
    @Excel(name = "当前状态流程号", width = 20)
    private String currentProcessNo;
    //部门
    @Excel(name = "部门", width = 20)
    private String dept;
    //员工姓名
    @Excel(name = "员工姓名", width = 20)
    private String staffName;
    //工作流编号
    @Excel(name = "工作流编号", width = 20)
    private String workflowId;
    //节约分类
    @Excel(name = "节约分类", width = 20)
    private String cisCategory;
    //排序
    @Excel(name = "排序", width = 20)
    private Integer sortBy;
    @TableField(exist = false)
    private List<ProposalPicture> pictures;
    @TableField(exist = false)
    private List<Map<String, String>> pictureObjList;
    //上个流程
    @Excel(name = "上个流程", width = 20)
    private String preProcessNo;
    //奖励现金
    @Excel(name = "奖励现金", width = 20)
    private Double awardCash;
    //奖励积分
    @Excel(name = "奖励积分", width = 20)
    private Integer awardToken;
    //奖励因子
    @Excel(name = "奖励因子", width = 20)
    private Double awardDivisor;
    //计算方式
    @Excel(name = "计算方式", width = 20)
    private String calculateSaveMethod;
    //备份
    @Excel(name = "备份", width = 20)
    private String content;
    //参与度
    @Excel(name = "参与度", width = 20)
    private Double divisorParticipation;
    //难易程度
    @Excel(name = "难易程度", width = 20)
    private Double divisorDifficulty;
    //业务范围
    @Excel(name = "业务范围", width = 20)
    private Double divisorBusiness;
    //努力程度
    @Excel(name = "努力程度", width = 20)
    private Double divisorStrive;
    //创造因素
    @Excel(name = "创造因素", width = 20)
    private Double divisorCreativity;
    //利用度
    @Excel(name = "利用度", width = 20)
    private Double divisorAvailability;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
