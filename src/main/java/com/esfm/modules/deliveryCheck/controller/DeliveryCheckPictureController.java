package com.esfm.modules.deliveryCheck.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.deliveryCheck.entity.DeliveryCheckPicture;
import com.esfm.modules.deliveryCheck.service.DeliveryCheckPictureService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 发货检验的照片(DeliveryCheckPicture)表控制层
 *
 * @author makejava
 * @since 2021-09-30 08:51:53
 */
@RestController
@RequestMapping("deliveryCheckPicture")
public class DeliveryCheckPictureController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DeliveryCheckPictureService deliveryCheckPictureService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<DeliveryCheckPicture> page, DeliveryCheckPicture deliveryCheckPicture) {
        return success(this.deliveryCheckPictureService.page(page, new QueryWrapper<>(deliveryCheckPicture)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.deliveryCheckPictureService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody DeliveryCheckPicture deliveryCheckPicture) {
        return success(this.deliveryCheckPictureService.save(deliveryCheckPicture));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody DeliveryCheckPicture deliveryCheckPicture) {
        return success(this.deliveryCheckPictureService.updateById(deliveryCheckPicture));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.deliveryCheckPictureService.removeByIds(idList));
    }
}
