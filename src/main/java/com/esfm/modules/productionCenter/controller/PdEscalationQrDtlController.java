package com.esfm.modules.productionCenter.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.productionCenter.entity.PdDtl;
import com.esfm.modules.productionCenter.entity.PdEscalationQrDtl;
import com.esfm.modules.productionCenter.service.PdEscalationQrDtlService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 升级汇报流程-快速响应(PdEscalationQrDtl)表控制层
 *
 * @author yaoxin
 * @since 2021-12-03 22:32:18
 */
@RestController
@RequestMapping("pdEscalationQrDtl")
public class PdEscalationQrDtlController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PdEscalationQrDtlService pdEscalationQrDtlService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<PdEscalationQrDtl> page, PdEscalationQrDtl pdEscalationQrDtl) {
        return success(this.pdEscalationQrDtlService.page(page, new QueryWrapper<>(pdEscalationQrDtl)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.pdEscalationQrDtlService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody PdEscalationQrDtl pdEscalationQrDtl) {
        return success(this.pdEscalationQrDtlService.save(pdEscalationQrDtl));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody PdEscalationQrDtl pdEscalationQrDtl) {
        return success(this.pdEscalationQrDtlService.updateById(pdEscalationQrDtl));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pdEscalationQrDtlService.removeByIds(idList));
    }
    //提交新的qr
    @PostMapping(value = "submitQr")
    public R<?> submit (@RequestBody PdEscalationQrDtl pdEscalationQrDtl){
        return  this.pdEscalationQrDtlService.submit(pdEscalationQrDtl);
    }
    //跟新qr
    @PostMapping(value = "updateQr")
    public R<?> updateQr (@RequestBody PdEscalationQrDtl pdEscalationQrDtl){
        return  this.pdEscalationQrDtlService.updateQr(pdEscalationQrDtl);
    }
}