package com.esfm.modules.lpa.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.entity.LpaChecklistItem;
import com.esfm.modules.lpa.service.LpaChecklistItemService;
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
 * 分层审核-审核表-子项(LpaChecklistItem)表控制层
 *
 * @author yaoxin
 * @since 2020-07-30 11:28:46
 */
@RestController
@RequestMapping("lpaChecklistItem/")
public class LpaChecklistItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LpaChecklistItemService lpaChecklistItemService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param lpaChecklistItem 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<LpaChecklistItem> page, LpaChecklistItem lpaChecklistItem) {
        return success(this.lpaChecklistItemService.page(page, new QueryWrapper<>(lpaChecklistItem)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.lpaChecklistItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param lpaChecklistItem 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody LpaChecklistItem lpaChecklistItem) {
        return success(this.lpaChecklistItemService.save(lpaChecklistItem));
    }

    /**
     * 修改数据
     *
     * @param lpaChecklistItem 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody LpaChecklistItem lpaChecklistItem) {
        return success(this.lpaChecklistItemService.updateById(lpaChecklistItem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.lpaChecklistItemService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<LpaChecklistItem> res = lpaChecklistItemService.list();
        var exportXls = new ExportXls<LpaChecklistItem>("lpaChecklistItem" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaChecklistItem.class);
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
        ImportExcel<LpaChecklistItem> importExcel = new ImportExcel<>();
        return importExcel.importData(request, LpaChecklistItem.class, lpaChecklistItemService);
    }
}
