package com.zf.zf_server.modules.salary.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员历史薪资(SalaryHistory)表实体类
 *
 * @author makejava
 * @since 2021-06-01 19:40:39
 */
@Data
public class SalaryHistory extends Model<SalaryHistory> {
    //主键    
    @Excel(name = "主键", width = 20)
    private String id;
    //创建人登录名称    
    @Excel(name = "创建人登录名称", width = 20)
    private String createBy;
    //创建日期    
    @Excel(name = "创建日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新人登录名称    
    @Excel(name = "更新人登录名称", width = 20)
    private String updateBy;
    //更新日期    
    @Excel(name = "更新日期", width = 20, exportFormat = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //部门    
    @Excel(name = "部门", width = 20)
    private String department;
    //名字    
    @Excel(name = "名字", width = 20)
    private String name;
    //年    
    @Excel(name = "年", width = 20)
    private String year;
    //月    
    @Excel(name = "月", width = 20)
    private String month;
    //岗位工资    
    @Excel(name = "岗位工资", width = 20)
    private String salaryJob;
    //年功工资    
    @Excel(name = "年功工资", width = 20)
    private String salaryYear;
    //班长津贴    
    @Excel(name = "班长津贴", width = 20)
    private String subsidyMonitor;
    //交通补贴    
    @Excel(name = "交通补贴", width = 20)
    private String subsidyTraffic;
    //保健    
    @Excel(name = "保健", width = 20)
    private String subsidyHealth;
    //加班费    
    @Excel(name = "加班费", width = 20)
    private String subsidyOvertime;
    //夜班津贴    
    @Excel(name = "夜班津贴", width = 20)
    private String subsidyNight;
    //工作申请单    
    @Excel(name = "工作申请单", width = 20)
    private String workApplication;
    //补扣工资    
    @Excel(name = "补扣工资", width = 20)
    private String deductionSalary;
    //假    
    @Excel(name = "假", width = 20)
    private String holiday;
    //奖金    
    @Excel(name = "奖金", width = 20)
    private String bonus;
    //当月应计工资    
    @Excel(name = "当月应计工资", width = 20)
    private String payableSalary;
    //养老保险    
    @Excel(name = "养老保险", width = 20)
    private String pensionInsurance;
    //医疗保险    
    @Excel(name = "医疗保险", width = 20)
    private String medicalInsurance;
    //失业保险    
    @Excel(name = "失业保险", width = 20)
    private String unemploymentInsurance;
    //住房公积金    
    @Excel(name = "住房公积金", width = 20)
    private String housingFund;
    //失业险    
    @Excel(name = "失业险", width = 20)
    private String majorIllnessInsurance;
    //工资税    
    @Excel(name = "工资税", width = 20)
    private String tax;
    //当月实发工资    
    @Excel(name = "当月实发工资", width = 20)
    private String actualSalaryPaid;
    //独生子    
    @Excel(name = "独生子", width = 20)
    private String subsidyOnlyChild;
    //餐费    
    @Excel(name = "餐费", width = 20)
    private String subsidyMeal;
    //餐费
    @Excel(name = "企业年金个人", width = 20)
    private String corporateAnnuityIndividual;
    //模板主键    
    @Excel(name = "模板主键", width = 20)
    private String templateId;
    //用户主键    
    @Excel(name = "用户主键", width = 20)
    private String userId;
    //是否显示
    @Excel(name = "显示", width = 20)
    private String showEnable;
    //工号
    @Excel(name = "工号", width = 20)
    private String workCode;

    /**
     * 获取主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}