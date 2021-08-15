package com.zf.zf_server.modules.system.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysLog;
import com.zf.zf_server.modules.system.service.SysLogService;
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
 * 系统日志表(SysLog)表控制层
 *
 * @author yaoxin
 * @since 2020-05-27 15:14:53
 */
@RestController
@RequestMapping("sysLog/")
public class SysLogController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogService sysLogService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysLog 查询实体
     * @return 所有数据
     */
    @GetMapping(value="list")
    public R<?> selectAll(Page<SysLog> page, SysLog sysLog) {
        return success(this.sysLogService.page(page, new QueryWrapper<>(sysLog)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.sysLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysLog 实体对象
     * @return 新增结果
     */
    @PostMapping(value="insert")
    public R<?> insert(@RequestBody SysLog sysLog) {
        return success(this.sysLogService.save(sysLog));
    }

    /**
     * 修改数据
     *
     * @param sysLog 实体对象
     * @return 修改结果
     */
    @PutMapping(value="update")
    public R<?> update(@RequestBody SysLog sysLog) {
        return success(this.sysLogService.updateById(sysLog));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value="delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysLogService.removeByIds(idList));
    }

     /**
     * 导出excel
     * @param request 请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<SysLog> res=sysLogService.list();
        var exportXls= new ExportXls<SysLog>("sysLog"+ DateTime.now().toString("yyMMddHHmmss"),"demo","yaoxin");
        return exportXls.export(res,SysLog.class);
    }
    
     /**
     * 通过excel导入数据
     * @param request 请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<SysLog> importExcel=new ImportExcel<>();
        return importExcel.importData(request,SysLog.class,sysLogService);
    }
}