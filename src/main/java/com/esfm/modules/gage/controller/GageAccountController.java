package com.esfm.modules.gage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.gage.entity.GageAccount;
import com.esfm.modules.gage.service.GageAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 检具台账(GageAccount)表控制层
 *
 * @author makejava
 * @since 2021-10-24 21:17:53
 */
@RestController
@RequestMapping("gageAccount")
public class GageAccountController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private GageAccountService gageAccountService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<GageAccount> page, GageAccount gageAccount) {
        return success(this.gageAccountService.page(page, new QueryWrapper<>(gageAccount)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.gageAccountService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody GageAccount gageAccount) {
        return success(this.gageAccountService.save(gageAccount));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody GageAccount gageAccount) {
        return success(this.gageAccountService.updateById(gageAccount));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.gageAccountService.removeByIds(idList));
    }

    //获取近一个月需要校准的检具清单
    @GetMapping("needCalibrationInMonthList")
    public R<?> needCalibrationInMonthList(Page<GageAccount> page) {
        return this.gageAccountService.needCalibrationInMonthList(page);
    }

}
