package com.esfm.modules.lpa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.entity.LpaQuestion;
import com.esfm.modules.lpa.entity.vo.LpaQuestionDetailVo;

import java.util.List;

/**
 * 分层审核-审核问题(LpaQuestion)表服务接口
 * @author yaoxin
 * @since 2020-07-30 11:29:19
 */
public interface LpaQuestionService extends IService<LpaQuestion> {

    R<?> detail(Page<LpaQuestion> page);

    List<LpaQuestionDetailVo> detail();
}
