package com.esfm.modules.survey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.survey.entity.SurveyQuestion;
import com.esfm.modules.survey.entity.SurveyQuestionListVo;

import java.util.List;

/**
 * 问卷调查问题表(SurveyQuestion)表服务接口
 *
 * @author makejava
 * @since 2021-10-31 22:49:02
 */
public interface SurveyQuestionService extends IService<SurveyQuestion> {

    R<?> newSurveyQuestionList(List<SurveyQuestionListVo> surveyQuestionListVo);

    R<?> getSurveyQuestionListByOptions(String surveyId);
}
