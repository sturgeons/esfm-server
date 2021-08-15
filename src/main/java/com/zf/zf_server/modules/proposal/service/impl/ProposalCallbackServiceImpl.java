package com.zf.zf_server.modules.proposal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.proposal.dao.ProposalCallbackDao;
import com.zf.zf_server.modules.proposal.entity.ProposalCallback;
import com.zf.zf_server.modules.proposal.service.ProposalCallbackService;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysLoginInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 改善提案反馈(ProposalCallback)表服务实现类
 * @author yaoxin
 * @since 2020-04-24 16:20:46
 */
@Service("proposalCallbackService")
public class ProposalCallbackServiceImpl extends ServiceImpl<ProposalCallbackDao, ProposalCallback> implements ProposalCallbackService {

    @Resource
    private SysLoginInfoService sysLoginInfoService;

    @Override
    public R<?> getListByProposalId(Serializable id) {
        return R.ok(this.baseMapper.selectList(new QueryWrapper<ProposalCallback>().eq("proposal_id", id).orderByDesc("create_time")));
    }

    @Override
    public R<?> addNewCallBackSysUser(ProposalCallback proposalCallback, String token) {
        R<SysUser> staffList = sysLoginInfoService.getUser(token);
        if (!staffList.ok()) {
            return staffList;
        }
        proposalCallback.setCallbackName(staffList.getData().getName());
        this.baseMapper.insert(proposalCallback);
        return R.ok(proposalCallback);
    }
}
