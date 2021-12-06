package com.esfm.modules.productionCenter.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.productionCenter.entity.PdEscalationQrPicture;
import com.esfm.modules.productionCenter.service.PdEscalationQrPictureService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 生产数据-停机损失清单 (PdEscalationQrPicture)表控制层
 *
 * @author yaoxin
 * @since 2021-12-03 22:32:56
 */
@RestController
@RequestMapping("pdEscalationQrPicture")
public class PdEscalationQrPictureController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PdEscalationQrPictureService pdEscalationQrPictureService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<PdEscalationQrPicture> page, PdEscalationQrPicture pdEscalationQrPicture) {
        return success(this.pdEscalationQrPictureService.page(page, new QueryWrapper<>(pdEscalationQrPicture)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.pdEscalationQrPictureService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody PdEscalationQrPicture pdEscalationQrPicture) {
        return success(this.pdEscalationQrPictureService.save(pdEscalationQrPicture));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody PdEscalationQrPicture pdEscalationQrPicture) {
        return success(this.pdEscalationQrPictureService.updateById(pdEscalationQrPicture));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pdEscalationQrPictureService.removeByIds(idList));
    }
}