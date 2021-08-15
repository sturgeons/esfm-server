package com.zf.zf_server.modules.proposal.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.CommConstant;
import com.zf.zf_server.modules.proposal.entity.Proposal;
import com.zf.zf_server.modules.proposal.entity.bo.ProposalNextProcessBo;
import com.zf.zf_server.modules.proposal.service.ProposalService;
import com.zf.zf_server.utils.excel.ExportXls;
import com.zf.zf_server.utils.excel.ImportExcel;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 改善提案(Proposal)表控制层
 * @author yaoxin
 * @since 2020-04-24 16:20:36
 */

@RestController
@RequestMapping("proposal/")
public class ProposalController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProposalService proposalService;

    /**
     * 分页查询所有数据
     * @param page     分页对象
     * @param proposal 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<Proposal> page, Proposal proposal) {
        return success(this.proposalService.page(page, new QueryWrapper<>(proposal)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.proposalService.getById(id));
    }

    /**
     * 新增数据
     * @param proposal 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody Proposal proposal) {
        this.proposalService.save(proposal);
        return success(proposal);
    }

    /**
     * 修改数据
     * @param proposal 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody Proposal proposal) {
        return success(this.proposalService.updateById(proposal));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.proposalService.removeByIds(idList));
    }

    /**
     * 导出excel
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<Proposal> res = proposalService.list();
        var exportXls = new ExportXls<Proposal>("proposal" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, Proposal.class);
    }

    /**
     * 通过excel导入数据
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */

    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<Proposal> importExcel = new ImportExcel<>();
        return importExcel.importData(request, Proposal.class, proposalService);
    }

    /**
     * 分页查询所有数据 根据用户查询
     * @param page     分页对象
     * @param staff_id 员工id
     * @return 所有数据
     */
    @GetMapping(value = "list/{staff_id}")
    public R<?> selectListByStaffId(Page<Proposal> page, @PathVariable Serializable staff_id) {
        return success(this.proposalService.selectListByStaffId(page, staff_id));
    }

    /**
     * 上传照片
     * @param request 反馈
     * @return R 结果
     */
    @RequestMapping(value = "uploadPicture", method = RequestMethod.POST)
    public R<?> uploadPicture(@RequestParam(value = "file") MultipartFile file, MultipartHttpServletRequest request) {
        if (file.isEmpty()) {
            return failed("上传文件为空！");
        }
        return proposalService.uploadPicture(file,request);

    }


    /**
     * 提交新的提案
     * @param proposal 新提案
     * @return R 结果
     */
    @RequestMapping(value = "submitProposalSysUser", method = RequestMethod.POST)
    public R<?> submitProposalMobile(@RequestBody Proposal proposal, HttpServletRequest request) {

        return proposalService.submitProposalMobile(proposal, request.getHeader(CommConstant.REQUEST_TOKEN));
    }
    /**
     * 提案提交新的流程
     * @param request 新提案
     * @return R 结果
     */
    @RequestMapping(value = "nextProcess", method = RequestMethod.POST)
    public R<?> nextProcess(@RequestBody ProposalNextProcessBo proposalNextProcessBo, HttpServletRequest request) {
        return proposalService.nextProcess(proposalNextProcessBo, request.getHeader(CommConstant.REQUEST_TOKEN));
    }


    /**
     * 更新提案
     * @param proposal 新提案
     * @return R 结果
     */
    @RequestMapping(value = "updateProposalSysUser", method = RequestMethod.POST)
    public R<?> updateProposalSysUser(@RequestBody Proposal proposal, HttpServletRequest request) {
        return proposalService.updateProposalSysUser(proposal, request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("idAndPicture/{id}")
    public R<?> idAndPicture(@PathVariable Serializable id) {
        return this.proposalService.idAndPicture(id);
    }

    /**
     * 根据流程号查询意见清单
     * @param number 主键
     * @return 单条数据
     */
    @GetMapping("getProposalListByProcessNo/{number}")
    public R<?> getProposalListByProcessNo(Page<Proposal> page, @PathVariable Serializable number, HttpServletRequest request) {
        return this.proposalService.getProposalListByProcessNo(page,number.toString(), request.getHeader(CommConstant.REQUEST_TOKEN));
    }
    /**
     * 根据流程号查询意见清单
     * @return 单条数据
     */
    @GetMapping("getProposalListByProcessNo")
    public R<?> getProposalListByProcessNo(Page<Proposal> page, Proposal proposal , HttpServletRequest request) {
        return this.proposalService.getProposalListByProcessNo(page,proposal, request.getHeader(CommConstant.REQUEST_TOKEN));
    }
    /**
     * 回滚
     * @return 单条数据
     */
    @GetMapping("rollBack")
    public R<?> rollBack(@Param("id") String id) {
        return this.proposalService.rollBack(id);
    }
    /**
     * 获取个人提案数据
     * @param request 新提案
     * @return R 结果
     */
    @RequestMapping(value = "getPrivateProposalSysUser", method = RequestMethod.GET)
    public R<?> getPrivateProposalSysUser(Page<Proposal> page, HttpServletRequest request) {
        return proposalService.getPrivateProposalSysUser(page, request.getHeader(CommConstant.REQUEST_TOKEN));

    }

}
