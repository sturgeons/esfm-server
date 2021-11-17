package com.esfm.modules.proposal.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.CommConstant;
import com.esfm.modules.proposal.entity.ProposalCallback;
import com.esfm.modules.proposal.service.ProposalCallbackService;
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
 * 改善提案反馈(ProposalCallback)表控制层
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:46
 */
@RestController
@RequestMapping("proposalCallback/")
public class ProposalCallbackController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProposalCallbackService proposalCallbackService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param proposalCallback 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<ProposalCallback> page, ProposalCallback proposalCallback) {
        return success(this.proposalCallbackService.page(page, new QueryWrapper<>(proposalCallback)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.proposalCallbackService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param proposalCallback 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody ProposalCallback proposalCallback) {
        return success(this.proposalCallbackService.save(proposalCallback));
    }

    /**
     * 修改数据
     *
     * @param proposalCallback 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody ProposalCallback proposalCallback) {
        return success(this.proposalCallbackService.updateById(proposalCallback));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.proposalCallbackService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<ProposalCallback> res = proposalCallbackService.list();
        var exportXls = new ExportXls<ProposalCallback>("proposalCallback" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, ProposalCallback.class);
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
        ImportExcel<ProposalCallback> importExcel = new ImportExcel<>();
        return importExcel.importData(request, ProposalCallback.class, proposalCallbackService);
    }

    /**
     * 通过proposal id查询所有数据
     *
     * @param id proposal主键
     * @return 单条数据
     */
    @GetMapping("getListByProposalId/{id}")
    public R<?> getListByProposalId(@PathVariable Serializable id) {
        return this.proposalCallbackService.getListByProposalId(id);
    }

    /**
     * 通过proposal id查询所有数据
     *
     * @param request proposal主键
     * @return 单条数据
     */
    @RequestMapping(value = "addNewCallBackSysUser", method = RequestMethod.POST)
    public R<?> addNewCallBackSysUser(@RequestBody ProposalCallback proposalCallback, HttpServletRequest request) {
        return this.proposalCallbackService.addNewCallBackSysUser(proposalCallback, request.getHeader(CommConstant.REQUEST_TOKEN));
    }
}
