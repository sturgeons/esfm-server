package com.zf.zf_server.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.system.dao.SysUserRoleDao;
import com.zf.zf_server.modules.system.entity.SysUserRole;
import com.zf.zf_server.modules.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户对应的角色表(SysUserRole)表服务实现类
 * @author yaoxin
 * @since 2020-07-20 13:05:32
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

}
