package com.esfm.modules.survey.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.survey.entity.SurveyOptions;
import com.esfm.modules.survey.service.SurveyOptionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 问卷调查选项(SurveyOptions)表控制层
 *
 * @author makejava
 * @since 2021-10-31 22:49:29
 */
@RestController
@RequestMapping("surveyOptions")
public class SurveyOptionsController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SurveyOptionsService surveyOptionsService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SurveyOptions> page, SurveyOptions surveyOptions) {
        return success(this.surveyOptionsService.page(page, new QueryWrapper<>(surveyOptions)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.surveyOptionsService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SurveyOptions surveyOptions) {
        return success(this.surveyOptionsService.save(surveyOptions));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SurveyOptions surveyOptions) {
        return success(this.surveyOptionsService.updateById(surveyOptions));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.surveyOptionsService.removeByIds(idList));
    }
}
