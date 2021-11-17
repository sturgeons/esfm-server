package com.esfm.modules.gage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.gage.entity.GageTransferLog;
import com.esfm.modules.gage.service.GageTransferLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 转库记录(GageTransferLog)表控制层
 *
 * @author makejava
 * @since 2021-10-24 21:19:26
 */
@RestController
@RequestMapping("gageTransferLog")
public class GageTransferLogController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private GageTransferLogService gageTransferLogService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<GageTransferLog> page, GageTransferLog gageTransferLog) {
        return success(this.gageTransferLogService.page(page, new QueryWrapper<>(gageTransferLog)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.gageTransferLogService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody GageTransferLog gageTransferLog) {
        return success(this.gageTransferLogService.save(gageTransferLog));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody GageTransferLog gageTransferLog) {
        return success(this.gageTransferLogService.updateById(gageTransferLog));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.gageTransferLogService.removeByIds(idList));
    }
}
