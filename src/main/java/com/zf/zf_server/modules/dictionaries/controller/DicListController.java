package com.zf.zf_server.modules.dictionaries.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.dictionaries.entity.DicList;
import com.zf.zf_server.modules.dictionaries.service.DicListService;
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
 * 字典清单(DicList)表控制层
 * @author yaoxin
 * @since 2020-08-07 16:58:54
 */
@RestController
@RequestMapping("dicList/")
public class DicListController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DicListService dicListService;

    /**
     * 分页查询所有数据
     * @param page    分页对象
     * @param dicList 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<DicList> page, DicList dicList) {
        return success(this.dicListService.page(page, new QueryWrapper<>(dicList)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.dicListService.getById(id));
    }

    /**
     * 新增数据
     * @param dicList 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody DicList dicList) {
        return success(this.dicListService.save(dicList));
    }

    /**
     * 修改数据
     * @param dicList 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody DicList dicList) {
        return success(this.dicListService.updateById(dicList));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.dicListService.removeByIds(idList));
    }

    /**
     * 导出excel
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<DicList> res = dicListService.list();
        var exportXls = new ExportXls<DicList>("dicList" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, DicList.class);
    }

    /**
     * 通过excel导入数据
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<DicList> importExcel = new ImportExcel<>();
        return importExcel.importData(request, DicList.class, dicListService);
    }
    /*
    * 根据name来获取 map
    * */
    @RequestMapping(value = "getMap",method = RequestMethod.GET)
    public  R<?> getMap(String name){
        return  this.dicListService.getMapByName(name);
    }
    /*
     * 根据name来获取 array
     * */
    @RequestMapping(value = "getArray",method = RequestMethod.GET)
    public  R<?> getArray(String name){
        return  this.dicListService.getArray(name);
    }
}
