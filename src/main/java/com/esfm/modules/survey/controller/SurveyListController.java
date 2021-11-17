package com.esfm.modules.survey.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.survey.entity.SurveyList;
import com.esfm.modules.survey.service.SurveyListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 问卷调查问卷表(SurveyList)表控制层
 *
 * @author makejava
 * @since 2021-10-31 18:43:32
 */
@RestController
@RequestMapping("surveyList")
public class SurveyListController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SurveyListService surveyListService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SurveyList> page, SurveyList surveyList) {
        return success(this.surveyListService.page(page, new QueryWrapper<>(surveyList)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.surveyListService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SurveyList surveyList) {
        return success(this.surveyListService.save(surveyList));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SurveyList surveyList) {
        return success(this.surveyListService.updateById(surveyList));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.surveyListService.removeByIds(idList));
    }


}
