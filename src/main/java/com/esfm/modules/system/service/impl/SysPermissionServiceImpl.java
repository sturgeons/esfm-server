package com.esfm.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.system.dao.SysPermissionDao;
import com.esfm.modules.system.entity.SysPermission;
import com.esfm.modules.system.service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限页面(SysPermission)表服务实现类
 *
 * @author yaoxin
 * @since 2020-05-27 14:51:47
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

}