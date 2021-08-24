package com.zf.zf_server.modules.system.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.CommConstant;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysUserService;
import com.zf.zf_server.utils.excel.ExportXls;
import com.zf.zf_server.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUser)表控制层
 *
 * @author yaoxin
 * @since 2020-05-27 15:42:13
 */
@RestController
@RequestMapping("sysUser/")
public class SysUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysUser 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SysUser> page, SysUser sysUser) {
        return success(this.sysUserService.page(page, new QueryWrapper<>(sysUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.sysUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SysUser sysUser) {
        return success(this.sysUserService.save(sysUser));
    }

    /**
     * 修改数据
     *
     * @param sysUser 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SysUser sysUser) {
        return success(this.sysUserService.updateById(sysUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysUserService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<SysUser> res = sysUserService.list();
        var exportXls = new ExportXls<SysUser>("sysUser" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, SysUser.class);
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
        ImportExcel<SysUser> importExcel = new ImportExcel<>();
        return importExcel.importData(request, SysUser.class, sysUserService);
    }

    /**
     * 通过主键获取角色列表
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getRoles")
    public R<?> selectOne(@RequestParam(name = "id") String id) {
        return this.sysUserService.getRoles(id);
    }

    /**
     * 新增数据
     *
     * @param userRolesObj 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "updateRoles")
    public R<?> insert(@RequestBody List<Map<String, String>> userRolesObj) {
        return this.sysUserService.updateRoles(userRolesObj);
    }

    /**
     * 通过主键获取角色列表
     */
    @GetMapping("getMenus")
    public R<?> getMenus(@RequestParam(name = "terminal") String terminal, HttpServletRequest request) {
        return this.sysUserService.getMenus(request.getHeader(CommConstant.REQUEST_TOKEN), terminal);
    }

    /**
     * 注册用户
     *
     * @param sysUser 实体对象
     * @return 注册
     */
    @PostMapping(value = "register")
    public R<?> register(@RequestBody SysUser sysUser) {
        if (sysUser != null) {
            return this.sysUserService.register(sysUser);
        } else {
            return R.failed(ResponseInfoConstant.OPERATE_FAIL);
        }
    }

    /**
     * 修改密码
     *
     * @param request 实体对象
     * @return 注册
     */
    @PostMapping(value = "changePassword")
    public R<?> changePassword(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
        return this.sysUserService.changePassword(map.get("oldPassword"), map.get("newPassword"), request.getHeader(CommConstant.REQUEST_TOKEN));
    }

}
