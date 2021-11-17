package com.esfm.modules.survey.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.survey.entity.SurveyShcedule;
import com.esfm.modules.survey.service.SurveyShceduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 问卷调查计划(SurveyShcedule)表控制层
 *
 * @author makejava
 * @since 2021-10-31 22:49:45
 */
@RestController
@RequestMapping("surveyShcedule")
public class SurveyShceduleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SurveyShceduleService surveyShceduleService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SurveyShcedule> page, SurveyShcedule surveyShcedule) {
        return success(this.surveyShceduleService.page(page, new QueryWrapper<>(surveyShcedule)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.surveyShceduleService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SurveyShcedule surveyShcedule) {
        return success(this.surveyShceduleService.save(surveyShcedule));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SurveyShcedule surveyShcedule) {
        return success(this.surveyShceduleService.updateById(surveyShcedule));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.surveyShceduleService.removeByIds(idList));
    }
}
