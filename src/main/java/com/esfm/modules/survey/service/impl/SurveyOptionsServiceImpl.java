package com.esfm.modules.survey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.survey.entity.SurveyOptions;
import com.esfm.modules.survey.dao.SurveyOptionsDao;
import com.esfm.modules.survey.service.SurveyOptionsService;
import org.springframework.stereotype.Service;

/**
 * 问卷调查选项(SurveyOptions)表服务实现类
 *
 * @author makejava
 * @since 2021-10-31 22:49:29
 */
@Service("surveyOptionsService")
public class SurveyOptionsServiceImpl extends ServiceImpl<SurveyOptionsDao, SurveyOptions> implements SurveyOptionsService {

}
