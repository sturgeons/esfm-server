package com.zf.zf_server.modules.vqe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.vqe.entity.Vqe;
import com.zf.zf_server.modules.vqe.service.VqeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Vqe主积分表
 * (Vqe)表控制层
 *
 * @author makejava
 * @since 2021-09-08 16:12:21
 */
@RestController
@RequestMapping("vqe")
public class VqeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private VqeService vqeService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<Vqe> page, Vqe vqe) {
        return success(this.vqeService.page(page, new QueryWrapper<>(vqe)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.vqeService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody Vqe vqe) {
        return success(this.vqeService.save(vqe));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody Vqe vqe) {
        return success(this.vqeService.updateById(vqe));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.vqeService.removeByIds(idList));
    }
}
