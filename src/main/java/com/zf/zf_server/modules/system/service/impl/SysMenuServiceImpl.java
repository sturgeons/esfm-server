package com.zf.zf_server.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.dao.SysMenuDao;
import com.zf.zf_server.modules.system.entity.SysMenu;
import com.zf.zf_server.modules.system.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * 系统菜单表(SysMenu)表服务实现类
 * @author yaoxin
 * @since 2020-07-01 14:42:55
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Override
    public R<?> selectAllByTree(Page<SysMenu> page) {
        //父级
        var fatherMenus = this.baseMapper.selectPage(page,new QueryWrapper<SysMenu>().eq("parent_id", "0"));
        if (fatherMenus != null) {
            for (SysMenu menu : fatherMenus.getRecords()) {
                var childrenMenu = this.baseMapper.selectList(new QueryWrapper<SysMenu>().eq("parent_id", menu.getId()));
                menu.setChildren(childrenMenu);
            }
        }
        return R.ok(fatherMenus);
    }
}
