package com.zf.zf_server.modules.proposal.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.proposal.entity.Proposal;
import com.zf.zf_server.modules.proposal.entity.bo.ProposalNextProcessBo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.Serializable;

/**
 * 改善提案(Proposal)表服务接口
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:34
 */
public interface ProposalService extends IService<Proposal> {

    Page<Proposal> selectListByStaffId(Page<Proposal> page, Serializable staff_id);

    R<?> uploadPicture(MultipartFile file, MultipartHttpServletRequest request);

    R<?> idAndPicture(Serializable id);

    R<?> getProposalListByProcessNo(Page<Proposal> page, String number, String header);

    R<?> getProposalListByProcessNo(Page<Proposal> page,Proposal proposal, String header);

    R<?> nextProcess(ProposalNextProcessBo proposalNextProcessBo, String header);

    R<?> rollBack(String id);

    R<?> submitProposalMobile(Proposal proposal, String header);

    R<?> getPrivateProposalSysUser(Page<Proposal> page, String header);

    R<?> updateProposalSysUser(Proposal proposal, String header);
}
