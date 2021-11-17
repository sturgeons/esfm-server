package com.esfm.modules.gage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.gage.entity.GageCalibrationFile;
import com.esfm.modules.gage.service.GageCalibrationFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 检具校准附件(GageCalibrationFile)表控制层
 *
 * @author makejava
 * @since 2021-10-24 21:19:10
 */
@RestController
@RequestMapping("gageCalibrationFile")
public class GageCalibrationFileController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private GageCalibrationFileService gageCalibrationFileService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<GageCalibrationFile> page, GageCalibrationFile gageCalibrationFile) {
        return success(this.gageCalibrationFileService.page(page, new QueryWrapper<>(gageCalibrationFile)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.gageCalibrationFileService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody GageCalibrationFile gageCalibrationFile) {
        return success(this.gageCalibrationFileService.save(gageCalibrationFile));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody GageCalibrationFile gageCalibrationFile) {
        return success(this.gageCalibrationFileService.updateById(gageCalibrationFile));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.gageCalibrationFileService.removeByIds(idList));
    }
}
