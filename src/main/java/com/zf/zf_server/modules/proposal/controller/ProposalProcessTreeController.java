package com.zf.zf_server.modules.proposal.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.proposal.entity.ProposalProcessTree;
import com.zf.zf_server.modules.proposal.service.ProposalProcessTreeService;
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
 * 改善提案流程树图(ProposalProcessTree)表控制层
 *
 * @author yaoxin
 * @since 2020-04-24 16:21:02
 */
@RestController
@RequestMapping("proposalProcessTree/")
public class ProposalProcessTreeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProposalProcessTreeService proposalProcessTreeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param proposalProcessTree 查询实体
     * @return 所有数据
     */
    @GetMapping(value="list")
    public R<?> selectAll(Page<ProposalProcessTree> page, ProposalProcessTree proposalProcessTree) {
        return success(this.proposalProcessTreeService.page(page, new QueryWrapper<>(proposalProcessTree)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.proposalProcessTreeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param proposalProcessTree 实体对象
     * @return 新增结果
     */
    @PostMapping(value="insert")
    public R<?> insert(@RequestBody ProposalProcessTree proposalProcessTree) {
        return success(this.proposalProcessTreeService.save(proposalProcessTree));
    }

    /**
     * 修改数据
     *
     * @param proposalProcessTree 实体对象
     * @return 修改结果
     */
    @PutMapping(value="update")
    public R<?> update(@RequestBody ProposalProcessTree proposalProcessTree) {
        return success(this.proposalProcessTreeService.updateById(proposalProcessTree));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value="delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.proposalProcessTreeService.removeByIds(idList));
    }

     /**
     * 导出excel
     * @param request 请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<ProposalProcessTree> res=proposalProcessTreeService.list();
        var exportXls= new ExportXls<ProposalProcessTree>("proposalProcessTree"+ DateTime.now().toString("yyMMddHHmmss"),"demo","yaoxin");
        return exportXls.export(res,ProposalProcessTree.class);
    }
     /**
     * 通过excel导入数据
     * @param request 请求
     * @param response 反馈
     * @return R 结果
     */

    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<ProposalProcessTree> importExcel=new ImportExcel<>();
        return importExcel.importData(request,ProposalProcessTree.class,proposalProcessTreeService);
    }
}