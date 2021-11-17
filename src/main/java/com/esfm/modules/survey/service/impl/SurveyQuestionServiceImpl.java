package com.esfm.modules.survey.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.modules.survey.dao.SurveyQuestionDao;
import com.esfm.modules.survey.entity.SurveyOptions;
import com.esfm.modules.survey.entity.SurveyQuestion;
import com.esfm.modules.survey.entity.SurveyQuestionListVo;
import com.esfm.modules.survey.service.SurveyOptionsService;
import com.esfm.modules.survey.service.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 问卷调查问题表(SurveyQuestion)表服务实现类
 *
 * @author makejava
 * @since 2021-10-31 22:49:02
 */
@Service("surveyQuestionService")
public class SurveyQuestionServiceImpl extends ServiceImpl<SurveyQuestionDao, SurveyQuestion> implements SurveyQuestionService {
    @Autowired
    private SurveyOptionsService surveyOptionsService;

    @Override
    public R<?> newSurveyQuestionList(List<SurveyQuestionListVo> surveyQuestionListVo) {
        System.out.println(surveyQuestionListVo);
        surveyQuestionListVo.forEach(t -> {
            SurveyQuestion surveyQuestion = new SurveyQuestion();
            BeanUtil.copyProperties(t, surveyQuestion);
            this.save(surveyQuestion);
            t.getOptions().forEach(option -> {
                option.setQuestionId(surveyQuestion.getId());
                surveyOptionsService.save(option);
            });
        });
        return R.ok(surveyQuestionListVo);
    }

    @Override
    public R<?> getSurveyQuestionListByOptions(String surveyId) {
        List<SurveyQuestion> surveyQuestions = getBaseMapper().selectList(new QueryWrapper<SurveyQuestion>().eq("survey_id", surveyId));
        List<SurveyQuestionListVo> surveyQuestionListVoList = new ArrayList<>();
        surveyQuestions.forEach(t -> {
            SurveyQuestionListVo surveyQuestionListVo = new SurveyQuestionListVo();
            BeanUtil.copyProperties(t, surveyQuestionListVo);
            List<SurveyOptions> surveyOptionsList;
            surveyOptionsList = surveyOptionsService.list(new QueryWrapper<SurveyOptions>().eq("question_id", t.getId()));
            surveyQuestionListVo.setOptions(surveyOptionsList);
            surveyQuestionListVoList.add(surveyQuestionListVo);
        });
        return R.ok(surveyQuestionListVoList);
    }
}
