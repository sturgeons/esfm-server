package com.esfm.modules.dictionaries.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.dictionaries.entity.DicItem;
import com.esfm.modules.dictionaries.service.DicItemService;
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
 * 字典子类(DicItem)表控制层
 *
 * @author yaoxin
 * @since 2020-08-07 16:59:14
 */
@RestController
@RequestMapping("dicItem/")
public class DicItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DicItemService dicItemService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param dicItem 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<DicItem> page, DicItem dicItem) {
        return success(this.dicItemService.page(page, new QueryWrapper<>(dicItem).orderByAsc("option_sort")));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.dicItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dicItem 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody DicItem dicItem) {
        return success(this.dicItemService.save(dicItem));
    }

    /**
     * 修改数据
     *
     * @param dicItem 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody DicItem dicItem) {
        return success(this.dicItemService.updateById(dicItem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.dicItemService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<DicItem> res = dicItemService.list();
        var exportXls = new ExportXls<DicItem>("dicItem" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, DicItem.class);
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
        ImportExcel<DicItem> importExcel = new ImportExcel<>();
        return importExcel.importData(request, DicItem.class, dicItemService);
    }
}
