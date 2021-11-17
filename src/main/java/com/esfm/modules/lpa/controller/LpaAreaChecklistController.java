package com.esfm.modules.lpa.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.entity.LpaAreaChecklist;
import com.esfm.modules.lpa.service.LpaAreaChecklistService;
import com.esfm.utils.excel.ExportXls;
import com.esfm.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 分层审核-区域和审核单匹配表(LpaAreaChecklist)表控制层
 *
 * @author yaoxin
 * @since 2020-07-30 11:27:59
 */
@RestController
@RequestMapping("lpaAreaChecklist/")
public class LpaAreaChecklistController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LpaAreaChecklistService lpaAreaChecklistService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param lpaAreaChecklist 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<LpaAreaChecklist> page, LpaAreaChecklist lpaAreaChecklist) {
        return success(this.lpaAreaChecklistService.page(page, new QueryWrapper<>(lpaAreaChecklist)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.lpaAreaChecklistService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param lpaAreaChecklist 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody LpaAreaChecklist lpaAreaChecklist) {
        return success(this.lpaAreaChecklistService.save(lpaAreaChecklist));
    }

    @PostMapping(value = "insertList")
    public R<?> insertList(@RequestBody List<LpaAreaChecklist> lpaAreaChecklist) {
        this.lpaAreaChecklistService.remove(new QueryWrapper<LpaAreaChecklist>().isNull("area_id"));
        this.lpaAreaChecklistService.remove(new QueryWrapper<LpaAreaChecklist>().eq("area_id", lpaAreaChecklist.get(0).getAreaId()));
        return success(this.lpaAreaChecklistService.saveBatch(lpaAreaChecklist));
    }

    /**
     * 修改数据
     *
     * @param lpaAreaChecklist 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody LpaAreaChecklist lpaAreaChecklist) {
        return success(this.lpaAreaChecklistService.updateById(lpaAreaChecklist));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.lpaAreaChecklistService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<LpaAreaChecklist> res = lpaAreaChecklistService.list();
        var exportXls = new ExportXls<LpaAreaChecklist>("lpaAreaChecklist" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaAreaChecklist.class);
    }

    /**
     * 通过excel导入数据
     *
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<LpaAreaChecklist> importExcel = new ImportExcel<>();
        return importExcel.importData(request, LpaAreaChecklist.class, lpaAreaChecklistService);
    }
}
