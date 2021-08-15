package com.zf.zf_server.modules.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.workflow.dao.WorkflowNodeDao;
import com.zf.zf_server.modules.workflow.entity.WorkflowNode;
import com.zf.zf_server.modules.workflow.service.WorkflowNodeService;
import org.springframework.stereotype.Service;

/**
 * 流程路线表(WorkflowNode)表服务实现类
 *
 * @author yaoxin
 * @since 2020-05-28 09:25:56
 */
@Service("workflowNodeService")
public class WorkflowNodeServiceImpl extends ServiceImpl<WorkflowNodeDao, WorkflowNode> implements WorkflowNodeService {

}