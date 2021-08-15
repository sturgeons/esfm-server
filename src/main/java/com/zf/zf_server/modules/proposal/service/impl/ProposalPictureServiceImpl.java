package com.zf.zf_server.modules.proposal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.proposal.dao.ProposalPictureDao;
import com.zf.zf_server.modules.proposal.entity.Proposal;
import com.zf.zf_server.modules.proposal.entity.ProposalPicture;
import com.zf.zf_server.modules.proposal.service.ProposalPictureService;
import org.springframework.stereotype.Service;

/**
 * 改善提案图片(ProposalPicture)表服务实现类
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:55
 */
@Service("proposalPictureService")
public class ProposalPictureServiceImpl extends ServiceImpl<ProposalPictureDao, ProposalPicture> implements ProposalPictureService {

    @Override
    public void updateProposalId(Proposal proposal) {
       if (proposal.getPictures()==null){
           return;
        }
        if (proposal.getPictures().size() > 0) {
            for (ProposalPicture pp : proposal.getPictures()) {
                pp.setProposalId(proposal.getId());
                updateById(pp);
            }
//            updateBatchById(proposal.getPictures());
        }
    }
}
