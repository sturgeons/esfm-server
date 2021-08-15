package com.zf.zf_server.modules.lpa.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.lpa.entity.LpaQuestion;
import com.zf.zf_server.modules.lpa.entity.vo.LpaQuestionDetailVo;
import com.zf.zf_server.modules.lpa.service.LpaQuestionService;
import com.zf.zf_server.utils.excel.ExportXls;
import com.zf.zf_server.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 分层审核-审核问题(LpaQuestion)表控制层
 * @author yaoxin
 * @since 2020-07-30 11:29:19
 */
@RestController
@RequestMapping("lpaQuestion/")
public class LpaQuestionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LpaQuestionService lpaQuestionService;

    /**
     * 分页查询所有数据
     * @param page        分页对象
     * @param lpaQuestion 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<LpaQuestion> page, LpaQuestion lpaQuestion) {
        return success(this.lpaQuestionService.page(page, new QueryWrapper<>(lpaQuestion)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.lpaQuestionService.getById(id));
    }

    /**
     * 新增数据
     * @param lpaQuestion 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody LpaQuestion lpaQuestion) {
        return success(this.lpaQuestionService.save(lpaQuestion));
    }
    @PostMapping(value = "inserts")
    public R<?> inserts(@RequestBody List<LpaQuestion> lpaQuestions) {
        return success(this.lpaQuestionService.saveBatch(lpaQuestions));
    }
    /**
     * 修改数据
     * @param lpaQuestion 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody LpaQuestion lpaQuestion) {
        return success(this.lpaQuestionService.updateById(lpaQuestion));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.lpaQuestionService.removeByIds(idList));
    }

    /**
     * 导出excel
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<LpaQuestion> res = lpaQuestionService.list();
        var exportXls = new ExportXls<LpaQuestion>("lpaQuestion" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaQuestion.class);
    }

    /**
     * 通过excel导入数据
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<LpaQuestion> importExcel = new ImportExcel<>();
        return importExcel.importData(request, LpaQuestion.class, lpaQuestionService);
    }

    @GetMapping(value = "detail")
    public R<?> detail(Page<LpaQuestion> page) {
        return this.lpaQuestionService.detail(page);
    }

    @GetMapping(value = "exportXls/detail")
    public ModelAndView exportXlsDetail(HttpServletRequest request, HttpServletResponse response) {
        List<LpaQuestionDetailVo> res = lpaQuestionService.detail();
        var exportXls = new ExportXls<LpaQuestionDetailVo>("lpaQuestion" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaQuestionDetailVo.class);
    }
}
