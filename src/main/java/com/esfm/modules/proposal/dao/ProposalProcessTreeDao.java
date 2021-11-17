package com.esfm.modules.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esfm.modules.proposal.entity.ProposalProcessTree;

import java.util.List;

/**
 * 改善提案流程树图(ProposalProcessTree)表数据库访问层
 *
 * @author yaoxin
 * @since 2020-04-24 16:21:02
 */
public interface ProposalProcessTreeDao extends BaseMapper<ProposalProcessTree> {

//    List<ProposalProcessTree> selectProcessTreeByStaffId(String staff_id);
}
