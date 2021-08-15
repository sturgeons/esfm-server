package com.zf.zf_server.modules.salary.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.CommConstant;
import com.zf.zf_server.modules.salary.entity.SalaryHistory;
import com.zf.zf_server.modules.salary.service.SalaryHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 人员历史薪资(SalaryHistory)表控制层
 *
 * @author makejava
 * @since 2021-06-01 19:40:39
 */
@RestController
@RequestMapping("salaryHistory")
public class SalaryHistoryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SalaryHistoryService salaryHistoryService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SalaryHistory> page, SalaryHistory salaryHistory) {
        return success(this.salaryHistoryService.page(page, new QueryWrapper<>(salaryHistory)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.salaryHistoryService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SalaryHistory salaryHistory) {
        return success(this.salaryHistoryService.save(salaryHistory));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SalaryHistory salaryHistory) {
        return success(this.salaryHistoryService.updateById(salaryHistory));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.salaryHistoryService.removeByIds(idList));
    }

    /**
     * 提交数据
     */
    @PostMapping(value = "upload")
    public R<?> upload(@RequestBody List<SalaryHistory> SalaryHistorys) {
        return success(this.salaryHistoryService.upload(SalaryHistorys));
    }
    //获取个人薪资历史清单
    @GetMapping(value = "getHistorys")
    public R<?> getHistorys(HttpServletRequest request) {
        return success(this.salaryHistoryService.getHistorys(request.getHeader(CommConstant.REQUEST_TOKEN)));
    }
}