package com.esfm.modules.lpa.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.dao.LpaQuestionDao;
import com.esfm.modules.lpa.entity.LpaQuestion;
import com.esfm.modules.lpa.entity.vo.LpaQuestionDetailVo;
import com.esfm.modules.lpa.service.LpaQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分层审核-审核问题(LpaQuestion)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:29:19
 */
@Service("lpaQuestionService")
public class LpaQuestionServiceImpl extends ServiceImpl<LpaQuestionDao, LpaQuestion> implements LpaQuestionService {

    @Override
    public R<?> detail(Page<LpaQuestion> page) {
       return R.ok( this.baseMapper.detail(page));
    }
    @Override
    public List<LpaQuestionDetailVo> detail() {
        return this.baseMapper.exportDetail();
    }
}
