package com.esfm.modules.productionCenter.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.productionCenter.entity.PdLostDic;
import com.esfm.modules.productionCenter.service.PdLostDicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 生产数据-停机损失清单 (PdLostDic)表控制层
 *
 * @author yaoxin
 * @since 2021-11-18 16:42:26
 */
@RestController
@RequestMapping("pdLostDic")
public class PdLostDicController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PdLostDicService pdLostDicService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<PdLostDic> page, PdLostDic pdLostDic) {
        return success(this.pdLostDicService.page(page, new QueryWrapper<>(pdLostDic)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.pdLostDicService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody PdLostDic pdLostDic) {
        return success(this.pdLostDicService.save(pdLostDic));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody PdLostDic pdLostDic) {
        return success(this.pdLostDicService.updateById(pdLostDic));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pdLostDicService.removeByIds(idList));
    }
}