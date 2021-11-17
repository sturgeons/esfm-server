package com.esfm.modules.survey.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.survey.entity.SurveyQuestionListVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.survey.entity.SurveyQuestion;
import com.esfm.modules.survey.service.SurveyQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 问卷调查问题表(SurveyQuestion)表控制层
 *
 * @author makejava
 * @since 2021-10-31 22:49:02
 */
@RestController
@RequestMapping("surveyQuestion")
public class SurveyQuestionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SurveyQuestionService surveyQuestionService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SurveyQuestion> page, SurveyQuestion surveyQuestion) {
        return success(this.surveyQuestionService.page(page, new QueryWrapper<>(surveyQuestion)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.surveyQuestionService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SurveyQuestion surveyQuestion) {
        return success(this.surveyQuestionService.save(surveyQuestion));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SurveyQuestion surveyQuestion) {
        return success(this.surveyQuestionService.updateById(surveyQuestion));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.surveyQuestionService.removeByIds(idList));
    }

    /**
     * 新增调查问卷
     */
    @PostMapping(value = "newSurveyQuestionList")
    public R<?> newSurveyQuestionList(@RequestBody List<SurveyQuestionListVo> surveyQuestionListVo) {
        return this.surveyQuestionService.newSurveyQuestionList(surveyQuestionListVo);
    }

    /**
     * 新增调查问卷
     */
    @GetMapping(value = "getSurveyQuestionListByOptions")
    public R<?> getSurveyQuestionListByOptions(@RequestParam String surveyId) {
        return this.surveyQuestionService.getSurveyQuestionListByOptions(surveyId);
    }
}
