package com.esfm.modules.proposal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.proposal.entity.ProposalCallback;

import java.io.Serializable;

/**
 * 改善提案反馈(ProposalCallback)表服务接口
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:46
 */
public interface ProposalCallbackService extends IService<ProposalCallback> {

    R<?> getListByProposalId(Serializable id);

    R<?> addNewCallBackSysUser(ProposalCallback proposalCallback, String header);
}
