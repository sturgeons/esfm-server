package com.esfm.modules.vqe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.CommConstant;
import com.esfm.modules.vqe.entity.Vqe;
import com.esfm.modules.vqe.service.VqeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    //提交签到
    @PostMapping(value = "sign")
    public R<?> sign(HttpServletRequest request) {
        return this.vqeService.sign(request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    //获取签到记录
    @GetMapping(value = "getSign")
    public R<?> getSign(@RequestParam("year") String year, @RequestParam("month") String month, HttpServletRequest request) {
        return this.vqeService.getSign(request.getHeader(CommConstant.REQUEST_TOKEN), year, month);
    }

    //获取签到记录
    @GetMapping(value = "getAllSign")
    public R<?> getAllSign(HttpServletRequest request) {
        return this.vqeService.getAllSign(request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    //添加培训记录
    @PostMapping(value = "signTrain")
    public R<?> signTrain(HttpServletRequest request) {
        return this.vqeService.signTrain(request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    //获取培训记录
    @GetMapping(value = "getSignTrain")
    public R<?> getSignTrain(HttpServletRequest request) {
        return this.vqeService.getSignTrain(request.getHeader(CommConstant.REQUEST_TOKEN));
    }
    //获取本周是否培训
    @GetMapping(value = "getSignTrainWeek")
    public R<?> getSignTrainWeek(HttpServletRequest request) {
        return this.vqeService.getSignTrainWeek(request.getHeader(CommConstant.REQUEST_TOKEN));
    }
}
