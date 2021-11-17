package com.esfm.modules.survey.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 问卷调查问题表(SurveyQuestion)表实体类
 *
 * @author makejava
 * @since 2021-10-31 22:57:58
 */
@Data
public class SurveyQuestionListVo {
    //主键
    private String id;
    //创建人登录名称
    private String createBy;
    //创建日期
    private Date createTime;
    //更新人登录名称
    private String updateBy;
    //更新日期
    private Date updateTime;
    //问卷id
    private String surveyId;
    //问题流水号
    private Integer sortNum;
    //题目类型
    private String questionType;
    //答案
    private String answer;
    //积分
    private Integer coin;
    //问题模板
    private String questionTemplate;
    //题干
    private String context;
    //辅助信息
    private String subContext;
    //合格介绍图片
    private String pictureOk;
    //不合格介绍图片
    private String pictureNok;
    private Boolean visible;
    private List<SurveyOptions> options;

}
