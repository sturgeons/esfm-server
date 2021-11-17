package com.esfm.modules.system.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.system.service.SysMenuService;
import com.esfm.modules.system.entity.SysMenu;
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
 * 系统菜单表(SysMenu)表控制层
 *
 * @author yaoxin
 * @since 2020-07-01 14:42:55
 */
@RestController
@RequestMapping("sysMenu/")
public class SysMenuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysMenu 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SysMenu> page, SysMenu sysMenu) {
        return success(this.sysMenuService.page(page, new QueryWrapper<>(sysMenu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.sysMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SysMenu sysMenu) {
        return success(this.sysMenuService.save(sysMenu));
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SysMenu sysMenu) {
        return success(this.sysMenuService.updateById(sysMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysMenuService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<SysMenu> res = sysMenuService.list();
        var exportXls = new ExportXls<SysMenu>("sysMenu" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, SysMenu.class);
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
        ImportExcel<SysMenu> importExcel = new ImportExcel<>();
        return importExcel.importData(request, SysMenu.class, sysMenuService);
    }

    /**
     * 分页查询所有数据树状结构
     */
    @GetMapping(value = "tree")
    public R<?> selectAllByTree(Page<SysMenu> page) {
        return this.sysMenuService.selectAllByTree(page);
    }

    //分页查询菜单的树状结构
    @GetMapping(value = "treeBySysMenu")
    public R<?> selectAllByTreeAndObj(Page<SysMenu> page, SysMenu sysMenu) {
        return this.sysMenuService.selectAllByTree(page, sysMenu);
    }
}
