package com.zf.zf_server.modules.lpa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.lpa.entity.LpaQuestion;
import com.zf.zf_server.modules.lpa.entity.vo.LpaQuestionDetailVo;

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
