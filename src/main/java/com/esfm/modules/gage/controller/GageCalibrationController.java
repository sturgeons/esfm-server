package com.esfm.modules.gage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.gage.entity.GageCalibration;
import com.esfm.modules.gage.service.GageCalibrationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 检具校准记录(GageCalibration)表控制层
 *
 * @author makejava
 * @since 2021-10-24 21:18:50
 */
@RestController
@RequestMapping("gageCalibration")
public class GageCalibrationController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private GageCalibrationService gageCalibrationService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<GageCalibration> page, GageCalibration gageCalibration) {
        return success(this.gageCalibrationService.page(page, new QueryWrapper<>(gageCalibration)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.gageCalibrationService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody GageCalibration gageCalibration) {
        return success(this.gageCalibrationService.save(gageCalibration));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody GageCalibration gageCalibration) {
        return success(this.gageCalibrationService.updateById(gageCalibration));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.gageCalibrationService.removeByIds(idList));
    }
}
