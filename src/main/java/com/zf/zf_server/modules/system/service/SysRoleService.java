package com.zf.zf_server.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysMenuRole;
import com.zf.zf_server.modules.system.entity.SysRole;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author yaoxin
 * @since 2020-06-29 16:49:48
 */
public interface SysRoleService extends IService<SysRole> {
    R<?> getMenu(String id);



    R<?> changeMenu(List<SysMenuRole> params);
}
