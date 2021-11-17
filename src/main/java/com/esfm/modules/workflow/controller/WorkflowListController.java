package com.esfm.modules.workflow.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.workflow.entity.WorkflowList;
import com.esfm.modules.workflow.service.WorkflowListService;
import com.esfm.utils.excel.ExportXls;
import com.esfm.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;


/**
 * 流程列表(WorkflowList)表控制层
 *
 * @author yaoxin
 * @since 2020-05-28 09:25:37
 */
@RestController
@RequestMapping("workflowList/")
public class WorkflowListController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WorkflowListService workflowListService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param workflowList 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<WorkflowList> page, WorkflowList workflowList) {
        return success(this.workflowListService.page(page, new QueryWrapper<>(workflowList)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.workflowListService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param workflowList 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody WorkflowList workflowList) {
        return success(this.workflowListService.save(workflowList));
    }

    /**
     * 修改数据
     *
     * @param workflowList 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody WorkflowList workflowList) {
        return success(this.workflowListService.updateById(workflowList));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.workflowListService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<WorkflowList> res = workflowListService.list();
        var exportXls = new ExportXls<WorkflowList>("workflowList" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, WorkflowList.class);
    }

    /**
     * 通过excel导入数据
     *
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<WorkflowList> importExcel = new ImportExcel<>();
        return importExcel.importData(request, WorkflowList.class, workflowListService);
    }
}