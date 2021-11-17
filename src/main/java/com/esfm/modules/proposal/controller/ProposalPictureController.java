package com.esfm.modules.proposal.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.proposal.entity.ProposalPicture;
import com.esfm.modules.proposal.service.ProposalPictureService;
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
 * 改善提案图片(ProposalPicture)表控制层
 *
 * @author yaoxin
 * @since 2020-04-24 16:20:55
 */
@RestController
@RequestMapping("proposalPicture/")
public class ProposalPictureController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProposalPictureService proposalPictureService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param proposalPicture 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<ProposalPicture> page, ProposalPicture proposalPicture) {
        return success(this.proposalPictureService.page(page, new QueryWrapper<>(proposalPicture)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.proposalPictureService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param proposalPicture 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody ProposalPicture proposalPicture) {
        return success(this.proposalPictureService.save(proposalPicture));
    }

    /**
     * 修改数据
     *
     * @param proposalPicture 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody ProposalPicture proposalPicture) {
        return success(this.proposalPictureService.updateById(proposalPicture));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.proposalPictureService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<ProposalPicture> res = proposalPictureService.list();
        var exportXls = new ExportXls<ProposalPicture>("proposalPicture" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, ProposalPicture.class);
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
        ImportExcel<ProposalPicture> importExcel = new ImportExcel<>();
        return importExcel.importData(request, ProposalPicture.class, proposalPictureService);
    }
}