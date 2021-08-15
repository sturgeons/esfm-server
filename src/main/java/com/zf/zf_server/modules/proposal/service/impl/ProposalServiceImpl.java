package com.zf.zf_server.modules.proposal.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.proposal.dao.ProposalDao;
import com.zf.zf_server.modules.proposal.entity.Proposal;
import com.zf.zf_server.modules.proposal.entity.ProposalCallback;
import com.zf.zf_server.modules.proposal.entity.ProposalPicture;
import com.zf.zf_server.modules.proposal.entity.bo.ProposalNextProcessBo;
import com.zf.zf_server.modules.proposal.service.ProposalCallbackService;
import com.zf.zf_server.modules.proposal.service.ProposalPictureService;
import com.zf.zf_server.modules.proposal.service.ProposalService;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysLoginInfoService;
import com.zf.zf_server.modules.wizard.entity.bo.ObsBo;
import com.zf.zf_server.modules.wizard.service.ObsService;
import com.zf.zf_server.modules.workflow.entity.WorkflowList;
import com.zf.zf_server.modules.workflow.entity.WorkflowNode;
import com.zf.zf_server.modules.workflow.service.WorkflowListService;
import com.zf.zf_server.modules.workflow.service.WorkflowNodeService;
import com.zf.zf_server.utils.WorkFlowParse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * 改善提案(Proposal)表服务实现类
 * @author yaoxin
 * @since 2020-04-24 16:20:35
 */
@Service("proposalService")
public class ProposalServiceImpl extends ServiceImpl<ProposalDao, Proposal> implements ProposalService {
    @Resource
    private ProposalPictureService proposalPictureService;
    @Resource
    private ProposalCallbackService proposalCallbackService;
    @Resource
    private WorkflowNodeService workflowNodeService;

    @Resource
    private WorkflowListService workflowListService;

    @Resource
    private ObsService obsService;

    //员工登录信息服务
    @Resource
    private SysLoginInfoService sysLoginInfoService;

    @Override
    public Page<Proposal> selectListByStaffId(Page<Proposal> page, Serializable staff_id) {
        return this.page(page, new QueryWrapper<Proposal>().eq("staff_id", staff_id));
    }

    @Override
    public R<?> uploadPicture(MultipartFile file, MultipartHttpServletRequest request) {
        R<?> res=obsService.upload(file);
        if (res.ok()){
            ProposalPicture proposalPicture = new ProposalPicture();
            ObsBo data= (ObsBo) res.getData();
            proposalPicture.setFileName(data.getMd5Filename());
            proposalPicture.setUrl(data.getUrl());
            proposalPicture.setProposalId(request.getParameter("id"));
            //更新改善事件的图片数据库信息
            proposalPictureService.save(proposalPicture);
            return R.ok(proposalPicture);
        }
        return  res;

    }

    /*
     * 获取改善意见照片
     * */
    @Override
    public R<?> idAndPicture(Serializable id) {
        Proposal proposal = this.baseMapper.selectById(id);
        List<ProposalPicture> pictures = proposalPictureService
                .getBaseMapper()
                .selectList(new QueryWrapper<ProposalPicture>().eq("proposal_id", id));
        List<Map<String, String>> picturesMap = new ArrayList<>();
        if (pictures == null) {
            return R.ok(proposal);
        }
        for (ProposalPicture pp : pictures) {
            Map<String, String> map = new HashMap<>();
            map.put("id", pp.getId());
            map.put("name", pp.getFileName());
            map.put("status", "done");
            map.put("url",pp.getUrl());
            picturesMap.add(map);
            proposal.setPictureObjList(picturesMap);
        }
        return R.ok(proposal);
    }

    @Override
    public R<?> getProposalListByProcessNo(Page<Proposal> page, String number, String header) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(header);
        if (!sysUserR.ok()) return sysUserR;
        Map<String, String> maps = new HashMap<>();
        maps.put("current_process_no", number);
        maps.put("dept", sysUserR.getData().getDept());
        return R.ok(this.page(page, new QueryWrapper<Proposal>().allEq(maps).orderByDesc("propose_time")));
    }
    @Override
    public R<?> getProposalListByProcessNo(Page<Proposal> page,Proposal proposal, String header) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(header);
        if (!sysUserR.ok()) return sysUserR;
        proposal.setDept(sysUserR.getData().getDept());
        return R.ok(this.page(page, new QueryWrapper<>(proposal).orderByDesc("propose_time")));
    }

    /**
     * 改善意见推进下一流程
     */
    @Override
    public R<?> nextProcess(ProposalNextProcessBo proposalNextProcessBo, String header) {
        return autoRollProcess(proposalNextProcessBo, true);
    }

    @Override
    public R<?> rollBack(String id) {
        Proposal proposal = getBaseMapper().selectById(id);
        proposal.setCurrentProcessNo(proposal.getPreProcessNo());
        getBaseMapper().updateById(proposal);
        return R.ok(proposal);
    }

    @Override
    public R<?> submitProposalMobile(Proposal proposal, String token) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(token);
        if (!sysUserR.ok()) {
            return sysUserR;
        }
        // 设置提案人
        proposal.setStaffId(sysUserR.getData().getId());
        proposal.setStaffName(sysUserR.getData().getName());
        //设置提案时间
        proposal.setProposeTime(new Date());
        //设置提案初始流程号
        proposal.setCurrentProcessNo("10");
        //设置部门
        proposal.setDept(sysUserR.getData().getDept());
        //设置状态
        proposal.setState("1");
        //设置流程id号
        proposal.setWorkflowId(workflowListService.getOne(new QueryWrapper<WorkflowList>().eq("name", "改善提案")).getId());
        if (this.baseMapper.insert(proposal) == 0) {
            return R.failed("数据插入失败！");
        }
        //更新图片 改善意见编号
        proposalPictureService.updateProposalId(proposal);
        //更近改善意见反馈信息
        ProposalCallback proposalCallback = new ProposalCallback();
        proposalCallback.setProposalId(proposal.getId());
        proposalCallback.setContext(" 提交了改善意见。");
        proposalCallback.setCallbackName(sysUserR.getData().getName());
        proposalCallbackService.save(proposalCallback);
        //存储完数据后 推进流程前进
        ProposalNextProcessBo proposalNextProcessBo = new ProposalNextProcessBo();
        proposalNextProcessBo.setParamsA("yes");
        proposalNextProcessBo.setProposalId(proposal.getId());
        nextProcess(proposalNextProcessBo, token);

        return R.ok(sysUserR);
    }

    @Override
    public R<?> getPrivateProposalSysUser(Page<Proposal> page, String token) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(token);
        if (!sysUserR.ok()) {
            return sysUserR;
        }
        Page<Proposal> proposals = this.baseMapper.selectPage(page, new QueryWrapper<Proposal>()
                .eq("staff_id", sysUserR.getData().getId())
                .orderByDesc("propose_time"));
        return R.ok(proposals);
    }

    @Override
    public R<?> updateProposalSysUser(Proposal proposal, String token) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(token);
        if (!sysUserR.ok()) {
            return sysUserR;
        }
        if (this.baseMapper.updateById(proposal) == 0) {
            return R.failed("数据插入失败！");
        }
        proposalPictureService.updateProposalId(proposal);
        ProposalCallback proposalCallback = new ProposalCallback();
        proposalCallback.setProposalId(proposal.getId());
        proposalCallback.setContext(" 更新了改善意见。");
        proposalCallback.setCallbackName(sysUserR.getData().getName());
        proposalCallbackService.save(proposalCallback);
        return R.ok(proposal);
    }

    /*
     * 自动滚动流程
     * */
    R<?> autoRollProcess(ProposalNextProcessBo proposalNextProcessBo, Boolean jumpFlag) {
        //获取proposal
        Proposal proposal = getBaseMapper().selectById(proposalNextProcessBo.getProposalId());
        //获取工作流信息
        Map<String, String> query = new HashMap<>();
        query.put("workflow_id", proposal.getWorkflowId());
        query.put("node_no", String.valueOf(proposal.getCurrentProcessNo()));
        WorkflowNode workflowNode = workflowNodeService.getOne(new QueryWrapper<WorkflowNode>().allEq(query));
        if (workflowNode == null) {
            return R.failed("error");
        }

        //下流程流程号
        String nextProcessNoStr = workflowNode.getNextnode();
        boolean doAction = true;
        if (!StrUtil.isEmpty(workflowNode.getFlowCondition())) {
            WorkFlowParse workFlowParse = new WorkFlowParse(workflowNode.getFlowCondition());
            Map<String, String> map = new HashMap<>();
            //解析条件参数
            String paramsA = proposalNextProcessBo.getParamsA();
            if (workFlowParse.getConditionCategory().equals("data")) {
                paramsA = getBaseMapper().selectParam(proposalNextProcessBo.getProposalId(), workFlowParse.getConditionExp());
            }
            map.put("a", paramsA);
            nextProcessNoStr = workFlowParse.getNextProcessNo(map);
            doAction = workFlowParse.isSuccess(map);
        }
        if (workflowNode.getType().equals("0")) {
            proposal.setPreProcessNo(proposal.getCurrentProcessNo());
        }
        proposal.setCurrentProcessNo(nextProcessNoStr);
        getBaseMapper().updateById(proposal);
        proposalNextProcessBo.setParamsA("");
        //解析action
        if (doAction && StrUtil.isNotEmpty(workflowNode.getAction())) {
            Map<String, String> maps = (Map<String, String>) JSON.parse(workflowNode.getAction());
            maps.put("id", proposal.getId());
            getBaseMapper().updateDynamicData(maps);
        }
        R<?> res = R.ok(getBaseMapper().selectById(proposal.getId()));
        // 获取下个流程的结构数据
        WorkflowNode nodeNo = workflowNodeService.getBaseMapper().selectOne(new QueryWrapper<WorkflowNode>().eq("node_no", nextProcessNoStr));
        // 如果不是流程终点 就往下走
        if (nodeNo.getType().equals("1")) {
            res = autoRollProcess(proposalNextProcessBo, true);
        }
        return res;
    }
}
