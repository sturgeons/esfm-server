package com.esfm.modules.productionCenter.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.productionCenter.entity.PdDtl;
import com.esfm.modules.productionCenter.service.PdDtlService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 生产数据详情表(PdDtl)表控制层
 *
 * @author yaoxin
 * @since 2021-11-18 16:34:26
 */
@RestController
@RequestMapping("pdDtl")
public class PdDtlController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PdDtlService pdDtlService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<PdDtl> page, PdDtl pdDtl) {
        return success(this.pdDtlService.page(page, new QueryWrapper<>(pdDtl)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.pdDtlService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody PdDtl pdDtl) {
        return success(this.pdDtlService.save(pdDtl));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody PdDtl pdDtl) {
        return success(this.pdDtlService.updateById(pdDtl));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pdDtlService.removeByIds(idList));
    }

}