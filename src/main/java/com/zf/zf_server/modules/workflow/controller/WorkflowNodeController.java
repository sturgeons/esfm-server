package com.zf.zf_server.modules.workflow.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.workflow.entity.WorkflowNode;
import com.zf.zf_server.modules.workflow.service.WorkflowNodeService;
import com.zf.zf_server.utils.excel.ExportXls;
import com.zf.zf_server.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;


/**
 * 流程路线表(WorkflowNode)表控制层
 *
 * @author yaoxin
 * @since 2020-05-28 09:25:56
 */
@RestController
@RequestMapping("workflowNode/")
public class WorkflowNodeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WorkflowNodeService workflowNodeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param workflowNode 查询实体
     * @return 所有数据
     */
    @GetMapping(value="list")
    public R<?> selectAll(Page<WorkflowNode> page, WorkflowNode workflowNode) {
        return success(this.workflowNodeService.page(page, new QueryWrapper<>(workflowNode)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.workflowNodeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param workflowNode 实体对象
     * @return 新增结果
     */
    @PostMapping(value="insert")
    public R<?> insert(@RequestBody WorkflowNode workflowNode) {
        return success(this.workflowNodeService.save(workflowNode));
    }

    /**
     * 修改数据
     *
     * @param workflowNode 实体对象
     * @return 修改结果
     */
    @PutMapping(value="update")
    public R<?> update(@RequestBody WorkflowNode workflowNode) {
        return success(this.workflowNodeService.updateById(workflowNode));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value="delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.workflowNodeService.removeByIds(idList));
    }

     /**
     * 导出excel
     * @param request 请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<WorkflowNode> res=workflowNodeService.list();
        var exportXls= new ExportXls<WorkflowNode>("workflowNode"+ DateTime.now().toString("yyMMddHHmmss"),"demo","yaoxin");
        return exportXls.export(res,WorkflowNode.class);
    }
    
     /**
     * 通过excel导入数据
     * @param request 请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<WorkflowNode> importExcel=new ImportExcel<>();
        return importExcel.importData(request,WorkflowNode.class,workflowNodeService);
    }
}