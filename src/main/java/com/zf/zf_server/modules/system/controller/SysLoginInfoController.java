package com.zf.zf_server.modules.system.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysLoginInfo;
import com.zf.zf_server.modules.system.service.SysLoginInfoService;
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
 * 登录历史(SysLoginInfo)表控制层
 *
 * @author yaoxin
 * @since 2020-07-12 13:54:03
 */
@RestController
@RequestMapping("sysLoginInfo/")
public class SysLoginInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysLoginInfoService sysLoginInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysLoginInfo 查询实体
     * @return 所有数据
     */
    @GetMapping(value="list")
    public R<?> selectAll(Page<SysLoginInfo> page, SysLoginInfo sysLoginInfo) {
        return success(this.sysLoginInfoService.page(page, new QueryWrapper<>(sysLoginInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.sysLoginInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysLoginInfo 实体对象
     * @return 新增结果
     */
    @PostMapping(value="insert")
    public R<?> insert(@RequestBody SysLoginInfo sysLoginInfo) {
        return success(this.sysLoginInfoService.save(sysLoginInfo));
    }

    /**
     * 修改数据
     *
     * @param sysLoginInfo 实体对象
     * @return 修改结果
     */
    @PutMapping(value="update")
    public R<?> update(@RequestBody SysLoginInfo sysLoginInfo) {
        return success(this.sysLoginInfoService.updateById(sysLoginInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value="delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysLoginInfoService.removeByIds(idList));
    }

     /**
     * 导出excel
     * @param request 请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<SysLoginInfo> res=sysLoginInfoService.list();
        var exportXls= new ExportXls<SysLoginInfo>("sysLoginInfo"+ DateTime.now().toString("yyMMddHHmmss"),"demo","yaoxin");
        return exportXls.export(res,SysLoginInfo.class);
    }
    
     /**
     * 通过excel导入数据
     * @param request 请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<SysLoginInfo> importExcel=new ImportExcel<>();
        return importExcel.importData(request,SysLoginInfo.class,sysLoginInfoService);
    }
}