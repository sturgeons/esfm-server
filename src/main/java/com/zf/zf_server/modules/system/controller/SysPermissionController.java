package com.zf.zf_server.modules.system.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysPermission;
import com.zf.zf_server.modules.system.service.SysPermissionService;
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
 * 权限页面(SysPermission)表控制层
 *
 * @author yaoxin
 * @since 2020-05-27 14:51:48
 */
@RestController
@RequestMapping("sysPermission/")
public class SysPermissionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysPermission 查询实体
     * @return 所有数据
     */
    @GetMapping(value="list")
    public R<?> selectAll(Page<SysPermission> page, SysPermission sysPermission) {
        return success(this.sysPermissionService.page(page, new QueryWrapper<>(sysPermission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.sysPermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实体对象
     * @return 新增结果
     */
    @PostMapping(value="insert")
    public R<?> insert(@RequestBody SysPermission sysPermission) {
        return success(this.sysPermissionService.save(sysPermission));
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实体对象
     * @return 修改结果
     */
    @PutMapping(value="update")
    public R<?> update(@RequestBody SysPermission sysPermission) {
        return success(this.sysPermissionService.updateById(sysPermission));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value="delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysPermissionService.removeByIds(idList));
    }

     /**
     * 导出excel
     * @param request 请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<SysPermission> res=sysPermissionService.list();
        var exportXls= new ExportXls<SysPermission>("sysPermission"+ DateTime.now().toString("yyMMddHHmmss"),"demo","yaoxin");
        return exportXls.export(res,SysPermission.class);
    }

     /**
     * 通过excel导入数据
     * @param request 请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<SysPermission> importExcel=new ImportExcel<>();
        return importExcel.importData(request,SysPermission.class,sysPermissionService);
    }
}
