package com.zf.zf_server.modules.lpa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.modules.lpa.entity.LpaQuestion;
import com.zf.zf_server.modules.lpa.entity.vo.LpaQuestionDetailVo;

import java.util.List;

/**
 * 分层审核-审核问题(LpaQuestion)表数据库访问层
 * @author yaoxin
 * @since 2020-07-30 11:29:19
 */
public interface LpaQuestionDao extends BaseMapper<LpaQuestion> {

    Page<LpaQuestionDetailVo> detail(Page<LpaQuestion> page);

    List<LpaQuestionDetailVo> exportDetail();
}
