package com.zf.zf_server.modules.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.dao.SysUserDao;
import com.zf.zf_server.modules.system.entity.*;
import com.zf.zf_server.modules.system.entity.vo.MenuChildrenVo;
import com.zf.zf_server.modules.system.entity.vo.MenuVo;
import com.zf.zf_server.modules.system.service.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUser)表服务实现类
 * @author yaoxin
 * @since 2020-05-27 15:42:13
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Resource
    private SysUserRoleService sysUserRoleService;
    //员工登录信息服务
    @Resource
    private SysLoginInfoService sysLoginInfoService;

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysMenuRoleService sysMenuRoleService;
    @Resource
    private  SysRoleService sysRoleService;
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
        if (sysUserRoles.size()!=0){
            sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", sysUserRoles.get(0).getUserId()));
            sysUserRoleService.saveBatch(sysUserRoles);
        }
        return R.ok(ResponseInfoConstant.OPERATE_SUCCESS);
    }

    @Override
    public R<?> getMenus(String token, String terminal) {
        // 获取登录用户信息
        R<SysUser> sysUser = sysLoginInfoService.getUser(token);
        if (!sysUser.ok()) {
            return sysUser;
        }
        // 获取用户的角色清单
        var ur = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id",sysUser.getData().getId()));
        // 初始化菜单列表
        List<SysMenu> menus = new ArrayList<>();
        //添加默认菜单
        var defaultMenu = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("name","默认"));
        //添加用户权限下的菜单
        var defaultChildrenMenus=sysMenuRoleService.list(new QueryWrapper<SysMenuRole>().eq("role_id",defaultMenu.getId()));
        addMenus(menus, defaultChildrenMenus);
        // 批量搜索角色对应的菜单
        ur.forEach(t -> {
            var menuRoles = sysMenuRoleService.list(new QueryWrapper<SysMenuRole>().eq("role_id",t.getRoleId()));
            addMenus(menus, menuRoles);
        });
        List<MenuVo> menuVos = new ArrayList<>();
        // 判断是否重复填充
        List<String> keyList = new ArrayList<>();
        // 获取父级列表
        menus.forEach(t -> {
            if (t.getParentId().equals("0")&&t.getTerminal().equals(terminal)) {
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
            if (!t.getParentId().equals("0")&&t.getTerminal().equals(terminal)) {
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
        menuVos.forEach(t-> t.getChildren().sort(Comparator.comparing(MenuChildrenVo::getSortNum)));
        menuVos.sort(Comparator.comparing(MenuVo::getSortNum));
        return R.ok(menuVos);
    }

    private void addMenus(List<SysMenu> menus, List<SysMenuRole> defaultChildrenMenus) {
        defaultChildrenMenus.forEach(i -> {
            //直接搜索对应的id
            SysMenu tempMenu=sysMenuService.getById(i.getMenuId());
            if (tempMenu!=null){
                menus.add(tempMenu);
                //用子级搜索父级添加到列表
                tempMenu=sysMenuService.getById(tempMenu.getParentId());
                if (tempMenu!=null){
                    menus.add(tempMenu);
                }
            }
        });
    }

    @Override
    public R<?> register(SysUser sysUser) {
        //检查手机号是否已经注册
        List<SysUser> users = this.baseMapper.selectList(new QueryWrapper<SysUser>().eq("tel", sysUser.getTel()));
        if (users.size()==0){
            int insert = this.baseMapper.insert(sysUser);
            if (insert>0){
                return R.ok(sysUser);
            }
        }
        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }
}
