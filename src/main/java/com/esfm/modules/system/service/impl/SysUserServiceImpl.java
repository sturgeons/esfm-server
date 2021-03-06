package com.esfm.modules.system.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.system.dao.SysUserDao;
import com.esfm.modules.system.entity.*;
import com.esfm.modules.system.entity.vo.MenuChildrenVo;
import com.esfm.modules.system.entity.vo.MenuVo;
import com.esfm.modules.system.service.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUser)表服务实现类
 *
 * @author yaoxin
 * @since 2020-05-27 15:42:13
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Resource
    private SysUserRoleService sysUserRoleService;
    //员工登录信息服务
    @Lazy
    @Resource
    private SysLoginInfoService sysLoginInfoService;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysMenuRoleService sysMenuRoleService;
    @Resource
    private SysRoleService sysRoleService;

    @Override
    public R<?> getRoles(String id) {
        List<SysUserRole> resList = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id", id));
        List<String> res = new ArrayList<>();
        resList.forEach(t -> res.add(t.getRoleId()));
        return R.ok(res);
    }

    @Override
    public R<?> updateRoles(List<Map<String, String>> userRolesObj) {
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        userRolesObj.forEach(t -> {
            var su = new SysUserRole();
            su.setRoleId(t.get("roleId"));
            su.setUserId(t.get("userId"));
            sysUserRoles.add(su);
        });
        if (sysUserRoles.size() != 0) {
            sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", sysUserRoles.get(0).getUserId()));
            sysUserRoleService.saveBatch(sysUserRoles);
        }
        return R.ok(ResponseInfoConstant.OPERATE_SUCCESS);
    }

    @Override
    public R<?> getMenus(String token, String terminal) {
        // 获取登录用户信息
        SysUser sysUser = sysLoginInfoService.getUser(token);
        // 获取用户的角色清单
        var ur = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
        // 初始化菜单列表
        List<SysMenu> menus = new ArrayList<>();
        //添加默认菜单
        var defaultMenu = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("name", "默认"));
        //添加用户权限下的菜单
        var defaultChildrenMenus = sysMenuRoleService.list(new QueryWrapper<SysMenuRole>().eq("role_id", defaultMenu.getId()));
        addMenus(menus, defaultChildrenMenus);
        // 批量搜索角色对应的菜单
        ur.forEach(t -> {
            var menuRoles = sysMenuRoleService.list(new QueryWrapper<SysMenuRole>().eq("role_id", t.getRoleId()));
            addMenus(menus, menuRoles);
        });
        List<MenuVo> menuVos = new ArrayList<>();
        // 判断是否重复填充
        List<String> keyList = new ArrayList<>();
        // 获取父级列表
        menus.forEach(t -> {
            if (t.getParentId().equals("0") && t.getTerminal().equals(terminal)) {
                // 如果包含则继续下一个
                if (keyList.contains(t.getId())) return;
                keyList.add(t.getId());
                MenuVo newMenu = new MenuVo();
                newMenu.setId(t.getId());
                newMenu.setIcon(t.getIcon());
                newMenu.setPath(t.getPath());
                newMenu.setTitle(t.getTitle());
                newMenu.setChildren(new ArrayList<>());
                newMenu.setSortNum(t.getSortNum());
                menuVos.add(newMenu);
            }
        });
        // 填充子级菜单
        menus.forEach(t -> {
            if (!t.getParentId().equals("0") && t.getTerminal().equals(terminal)) {
                menuVos.forEach(i -> {
                    // 如果父级id和 子级id树id相同
                    if (i.getId().equals(t.getParentId())) {
                        // 如果包含则继续下一个
                        if (keyList.contains(t.getId())) return;
                        keyList.add(t.getId());
                        MenuChildrenVo newMenu = new MenuChildrenVo();
                        newMenu.setId(t.getId());
                        newMenu.setIcon(t.getIcon());
                        newMenu.setPath(t.getPath());
                        newMenu.setTitle(t.getTitle());
                        newMenu.setSortNum(t.getSortNum());
                        i.getChildren().add(newMenu);
                    }
                });
            }
        });
        //根据sortNum排序
        menuVos.forEach(t -> t.getChildren().sort(Comparator.comparing(MenuChildrenVo::getSortNum)));
        menuVos.sort(Comparator.comparing(MenuVo::getSortNum));
        return R.ok(menuVos);
    }

    private void addMenus(List<SysMenu> menus, List<SysMenuRole> defaultChildrenMenus) {
        defaultChildrenMenus.forEach(i -> {
            //直接搜索对应的id
            SysMenu tempMenu = sysMenuService.getById(i.getMenuId());
            if (tempMenu != null) {
                menus.add(tempMenu);
                //用子级搜索父级添加到列表
                tempMenu = sysMenuService.getById(tempMenu.getParentId());
                if (tempMenu != null) {
                    menus.add(tempMenu);
                }
            }
        });
    }

    @Override
    public R<?> register(SysUser sysUser) {
        //检查手机号是否已经注册
        List<SysUser> users = this.baseMapper.selectList(new QueryWrapper<SysUser>().eq("tel", sysUser.getTel()));
        if (users.size() == 0) {
            int insert = this.baseMapper.insert(sysUser);
            if (insert > 0) {
                return R.ok(sysUser);
            }
        }
        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }


    @Override
    public R<?> changePassword(String oldPassword, String newPassword, String token) {
        // 获取登录用户信息
        SysUser sysUser = getUser(token);
         if (!sysUser.getPassword().equals(oldPassword)) {
            return R.failed(ResponseInfoConstant.NOT_SAME_OLD_PASSWORD);
        }
        sysUser.setPassword(newPassword);
        var res = this.updateById(sysUser);
        return res ? R.ok(sysUser) : R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public R<?> saveWxOpenId(String token, String openid) {
        // 获取登录用户信息
       SysUser sysUser = getUser(token);
        sysUser.setWxOpenid(openid);
        //更新openId
        int resCount = this.baseMapper.updateById(sysUser);
        return resCount > 0 ? R.ok(sysUser) : R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public SysUser getUserInfo(String token) {
        SysUser res = getUser(token);
        res.setPassword("");
        return res;
    }

    @Override
    public R<?> checkWxAuth(String token) {

        SysUser res = getUser(token);

        if (!StrUtil.isEmpty(res.getWxOpenid())) {
            return R.ok("微信已经注册");
        }
        return R.failed(ResponseInfoConstant.NO_WX_OPENID);
    }

    @Override
    public List<SysRole> getRolesEntityList(String userId) {
        List<SysUserRole> sysUserRoles = sysUserRoleService.lambdaQuery().eq(SysUserRole::getUserId, userId).list();
        List<SysRole> list = new ArrayList<>();
        sysUserRoles.forEach(sysUserRole -> list.add(sysRoleService.getById(sysUserRole.getRoleId())));
        return list;
    }

    private SysUser getUser(String token) {
        // 获取登录用户信息
        return sysLoginInfoService.getUser(token);


    }
}
