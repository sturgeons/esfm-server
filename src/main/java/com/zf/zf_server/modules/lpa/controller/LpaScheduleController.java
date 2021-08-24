package com.zf.zf_server.modules.lpa.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.CommConstant;
import com.zf.zf_server.modules.lpa.entity.LpaSchedule;
import com.zf.zf_server.modules.lpa.service.LpaScheduleService;
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
 * 分层审核-审核计划(LpaSchedule)表控制层
 *
 * @author yaoxin
 * @since 2020-07-30 11:30:11
 */
@RestController
@RequestMapping("lpaSchedule/")
public class LpaScheduleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LpaScheduleService lpaScheduleService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param lpaSchedule 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<LpaSchedule> page, LpaSchedule lpaSchedule) {
        return success(this.lpaScheduleService.page(page, new QueryWrapper<>(lpaSchedule).orderByDesc("plan_date")));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.lpaScheduleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param lpaSchedule 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody LpaSchedule lpaSchedule) {
        return success(this.lpaScheduleService.save(lpaSchedule));
    }

    @PostMapping(value = "new")
    public R<?> newSchedule(@RequestBody LpaSchedule lpaSchedule) {
        return this.lpaScheduleService.newSchedule(lpaSchedule);
    }

    /**
     * 修改数据
     *
     * @param lpaSchedule 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody LpaSchedule lpaSchedule) {
        return success(this.lpaScheduleService.updateById(lpaSchedule));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.lpaScheduleService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<LpaSchedule> res = lpaScheduleService.list();
        var exportXls = new ExportXls<LpaSchedule>("lpaSchedule" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaSchedule.class);
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
        ImportExcel<LpaSchedule> importExcel = new ImportExcel<>();
        return importExcel.importData(request, LpaSchedule.class, lpaScheduleService);
    }

    @GetMapping(value = "getPlanByUser")
    public R<?> getPlanByUser(Page<LpaSchedule> page, LpaSchedule lpaSchedule, HttpServletRequest request) {
        // TODO 获取自己的审核计划
        return success(this.lpaScheduleService.page(page, new QueryWrapper<>(lpaSchedule)));
    }

    //重审
    @GetMapping(value = "reAudit")
    public R<?> reAudit(@RequestParam String id) {
        return this.lpaScheduleService.reAudit(id);
    }

    //月计划
    @GetMapping(value = "month")
    public R<?> month(@RequestParam String month) {
        return this.lpaScheduleService.month(month);
    }

    //获取未审核计划清单
    @GetMapping(value = "getScheduleByUser")
    public R<?> getScheduleByUser(Page<LpaSchedule> page, HttpServletRequest request, @RequestParam(value = "status") String status) {
        return this.lpaScheduleService.getScheduleByUser(page, request.getHeader(CommConstant.REQUEST_TOKEN), status);
    }


    //获取未审核计划清单
    @GetMapping(value = "getCloseListByUser")
    public R<?> getCloseListByUser(Page<LpaSchedule> page, HttpServletRequest request) {
        return this.lpaScheduleService.getCloseListByUser(page, request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    @PostMapping(value = "close")
    public R<?> close(@RequestBody LpaSchedule lpaSchedule) {
        return this.lpaScheduleService.close(lpaSchedule);
    }

    @PostMapping(value = "noProduction")
    public R<?> noProduction(@RequestBody LpaSchedule lpaSchedule) {
        return this.lpaScheduleService.noProduction(lpaSchedule);
    }
}
