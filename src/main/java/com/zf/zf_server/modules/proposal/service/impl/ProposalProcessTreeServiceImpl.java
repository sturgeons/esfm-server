package com.zf.zf_server.modules.proposal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.proposal.dao.ProposalProcessTreeDao;
import com.zf.zf_server.modules.proposal.entity.ProposalProcessTree;
import com.zf.zf_server.modules.proposal.service.ProposalProcessTreeService;
import org.springframework.stereotype.Service;

/**
 * 改善提案流程树图(ProposalProcessTree)表服务实现类
 *
 * @author yaoxin
 * @since 2020-04-24 16:21:02
 */
@Service("proposalProcessTreeService")
public class ProposalProcessTreeServiceImpl extends ServiceImpl<ProposalProcessTreeDao, ProposalProcessTree> implements ProposalProcessTreeService {

}