package com.esfm.modules.system.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.CommConstant;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysUserService;
import com.esfm.utils.excel.ExportXls;
import com.esfm.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("list")
    public R<?> selectAll(Page<SysUser> page, SysUser sysUser) {
        return success(sysUserService.page(page, new QueryWrapper<>(sysUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(sysUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public R<?> insert(@RequestBody SysUser sysUser) {
        return success(sysUserService.save(sysUser));
    }

    /**
     * 修改数据
     *
     * @param sysUser 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public R<?> update(@RequestBody SysUser sysUser) {
        return success(sysUserService.updateById(sysUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(sysUserService.removeByIds(idList));
    }

    /**
     * 导出excel
     */
    @GetMapping("exportXls")
    public ModelAndView exportXls() {
        List<SysUser> res = sysUserService.list();
        var exportXls = new ExportXls<SysUser>("sysUser" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, SysUser.class);
    }

    /**
     * 通过excel导入数据
     *
     * @param request 请求
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request) {
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
    public R<?> getRoles(@RequestParam(name = "id") String id) {
        return sysUserService.getRoles(id);
    }

    /**
     * 新增数据
     *
     * @param userRolesObj 实体对象
     * @return 新增结果
     */
    @PostMapping("updateRoles")
    public R<?> insert(@RequestBody List<Map<String, String>> userRolesObj) {
        return sysUserService.updateRoles(userRolesObj);
    }

    /**
     * 通过主键获取角色列表
     */
    @GetMapping("getMenus")
    public R<?> getMenus(@RequestParam(name = "terminal") String terminal, HttpServletRequest request) {
        return sysUserService.getMenus(request.getHeader(CommConstant.REQUEST_TOKEN), terminal);
    }

    /**
     * 注册用户
     *
     * @param sysUser 实体对象
     * @return 注册
     */
    @PostMapping("register")
    public R<?> register(@RequestBody SysUser sysUser) {
        if (null != sysUser) {
            return sysUserService.register(sysUser);
        }
        return R.failed(ResponseInfoConstant.OPERATE_FAIL);

    }

    /**
     * 修改密码
     *
     * @param request 实体对象
     * @return 注册
     */
    @PostMapping("changePassword")
    public R<?> changePassword(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
        return sysUserService.changePassword(map.get("oldPassword"), map.get("newPassword"), request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    /**
     * 获取当前用户信息
     **/
    @GetMapping("getUserInfo")
    public R<?> getUserInfo(HttpServletRequest request) {
        return R.ok(sysUserService.getUserInfo(request.getHeader(CommConstant.REQUEST_TOKEN)));
    }

    /**
     * 检查微信是否已经注册openid
     **/
    @GetMapping("checkWxAuth")
    public R<?> checkWxAuth(HttpServletRequest request) {
        return sysUserService.checkWxAuth(request.getHeader(CommConstant.REQUEST_TOKEN));
    }

    /*
     * 检查token
     */
    @GetMapping("checkToken")
    public R<?> checkToken() {
        return R.ok(ResponseInfoConstant.OPERATE_SUCCESS);
    }

}
