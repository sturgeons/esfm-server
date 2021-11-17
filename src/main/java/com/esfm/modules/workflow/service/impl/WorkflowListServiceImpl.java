package com.esfm.modules.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.workflow.dao.WorkflowListDao;
import com.esfm.modules.workflow.entity.WorkflowList;
import com.esfm.modules.workflow.service.WorkflowListService;
import org.springframework.stereotype.Service;

/**
 * 流程列表(WorkflowList)表服务实现类
 *
 * @author yaoxin
 * @since 2020-05-28 09:25:37
 */
@Service("workflowListService")
public class WorkflowListServiceImpl extends ServiceImpl<WorkflowListDao, WorkflowList> implements WorkflowListService {

}