package com.esfm.modules.survey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.survey.entity.SurveyList;
import com.esfm.modules.survey.dao.SurveyListDao;
import com.esfm.modules.survey.service.SurveyListService;
import org.springframework.stereotype.Service;

/**
 * 问卷调查问卷表(SurveyList)表服务实现类
 *
 * @author makejava
 * @since 2021-10-31 18:43:32
 */
@Service("surveyListService")
public class SurveyListServiceImpl extends ServiceImpl<SurveyListDao, SurveyList> implements SurveyListService {

}
