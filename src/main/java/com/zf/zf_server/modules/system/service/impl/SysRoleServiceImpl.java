package com.zf.zf_server.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.dao.SysRoleDao;
import com.zf.zf_server.modules.system.entity.SysMenu;
import com.zf.zf_server.modules.system.entity.SysMenuRole;
import com.zf.zf_server.modules.system.entity.SysRole;
import com.zf.zf_server.modules.system.service.SysMenuRoleService;
import com.zf.zf_server.modules.system.service.SysMenuService;
import com.zf.zf_server.modules.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 * @author yaoxin
 * @since 2020-06-29 16:49:48
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Resource
    private SysMenuRoleService sysMenuRoleService;
    @Resource
    private SysMenuService sysMenuService;

    @Override
    public R<?> getMenu(String id) {
        List<String> res = new ArrayList<>();
        var sysMenuRoleList = sysMenuRoleService.list(new QueryWrapper<SysMenuRole>().eq("role_id", id));
        sysMenuRoleList.forEach(t -> {
            var fatherNode = sysMenuService.list(new QueryWrapper<SysMenu>().eq("parent_id", t.getMenuId()));
            if (fatherNode.size() == 0) {
                res.add(t.getMenuId());
            }
        });
        return R.ok(res);
    }

    @Override
    public R<?> changeMenu(List<SysMenuRole> params) {
        sysMenuRoleService.remove(new QueryWrapper<SysMenuRole>().eq("role_id", params.get(0).getRoleId()));
        sysMenuRoleService.saveBatch(params);
        return R.ok(ResponseInfoConstant.OPERATE_SUCCESS);
    }

}
