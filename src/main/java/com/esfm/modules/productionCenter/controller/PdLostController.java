package com.esfm.modules.productionCenter.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.productionCenter.entity.PdLost;
import com.esfm.modules.productionCenter.service.PdLostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 生产数据-停机损失清单 (PdLost)表控制层
 *
 * @author yaoxin
 * @since 2021-11-18 16:41:46
 */
@RestController
@RequestMapping("pdLost")
public class PdLostController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PdLostService pdLostService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<PdLost> page, PdLost pdLost) {
        return success(this.pdLostService.page(page, new QueryWrapper<>(pdLost)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.pdLostService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody PdLost pdLost) {
        return success(this.pdLostService.save(pdLost));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody PdLost pdLost) {
        return success(this.pdLostService.updateById(pdLost));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pdLostService.removeByIds(idList));
    }
}