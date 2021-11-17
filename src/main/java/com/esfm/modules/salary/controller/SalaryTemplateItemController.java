package com.esfm.modules.salary.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.salary.entity.SalaryTemplateItem;
import com.esfm.modules.salary.service.SalaryTemplateItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 人员历史薪资模板子类(SalaryTemplateItem)表控制层
 *
 * @author makejava
 * @since 2021-06-01 19:41:21
 */
@RestController
@RequestMapping("salaryTemplateItem")
public class SalaryTemplateItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SalaryTemplateItemService salaryTemplateItemService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SalaryTemplateItem> page, SalaryTemplateItem salaryTemplateItem) {
        return success(this.salaryTemplateItemService.page(page, new QueryWrapper<>(salaryTemplateItem)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.salaryTemplateItemService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SalaryTemplateItem salaryTemplateItem) {
        return success(this.salaryTemplateItemService.save(salaryTemplateItem));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SalaryTemplateItem salaryTemplateItem) {
        return success(this.salaryTemplateItemService.updateById(salaryTemplateItem));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.salaryTemplateItemService.removeByIds(idList));
    }

    //获取模板条目
    @GetMapping("getItems")
    public R<?> getItemByTemplateId(String templateId) {
        return this.salaryTemplateItemService.getItemByTemplateId(templateId);
    }
    //存储模板条目
    @PostMapping("save")
    public R<?> saveSalaryItems(@RequestBody List<SalaryTemplateItem> items) {
        return this.salaryTemplateItemService.saveSalaryItems(items);
    }

}