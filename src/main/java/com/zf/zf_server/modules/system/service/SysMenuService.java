package com.zf.zf_server.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysMenu;

/**
 * 系统菜单表(SysMenu)表服务接口
 *
 * @author yaoxin
 * @since 2020-07-01 14:42:55
 */
public interface SysMenuService extends IService<SysMenu> {

    R<?> selectAllByTree(Page<SysMenu> page);
}
