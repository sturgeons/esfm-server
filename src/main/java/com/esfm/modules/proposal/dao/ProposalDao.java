package com.esfm.modules.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esfm.modules.proposal.entity.Proposal;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 改善提案(Proposal)表数据库访问层
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:34
 */
public interface ProposalDao extends BaseMapper<Proposal> {

    String selectParam(String proposalId, String conditionExp);

    int updateDynamicData(@Param("maps") Map<String, String> maps);
}
