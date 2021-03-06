package com.esfm.modules.proposal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.modules.proposal.entity.Proposal;
import com.esfm.modules.proposal.entity.ProposalPicture;

/**
 * 改善提案图片(ProposalPicture)表服务接口
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:55
 */
public interface ProposalPictureService extends IService<ProposalPicture> {

    void updateProposalId(Proposal proposal);
}
