package com.esfm.modules.gage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.gage.entity.GageAccountFile;
import com.esfm.modules.gage.service.GageAccountFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 检具台账附件(GageAccountFile)表控制层
 *
 * @author makejava
 * @since 2021-10-24 21:18:28
 */
@RestController
@RequestMapping("gageAccountFile")
public class GageAccountFileController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private GageAccountFileService gageAccountFileService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<GageAccountFile> page, GageAccountFile gageAccountFile) {
        return success(this.gageAccountFileService.page(page, new QueryWrapper<>(gageAccountFile)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.gageAccountFileService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody GageAccountFile gageAccountFile) {
        return success(this.gageAccountFileService.save(gageAccountFile));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody GageAccountFile gageAccountFile) {
        return success(this.gageAccountFileService.updateById(gageAccountFile));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.gageAccountFileService.removeByIds(idList));
    }
}
