package com.zf.zf_server.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.system.dao.SysMenuRoleDao;
import com.zf.zf_server.modules.system.entity.SysMenuRole;
import com.zf.zf_server.modules.system.service.SysMenuRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色对应的菜单表(SysMenuRole)表服务实现类
 * @author yaoxin
 * @since 2020-07-16 10:48:02
 */
@Service("sysMenuRoleService")
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleDao, SysMenuRole> implements SysMenuRoleService {

}
