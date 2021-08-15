package com.zf.zf_server.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysLoginInfo;
import com.zf.zf_server.modules.system.entity.SysUser;

/**
 * 登录历史(SysLoginInfo)表服务接口
 *
 * @author yaoxin
 * @since 2020-07-12 13:54:03
 */
public interface SysLoginInfoService extends IService<SysLoginInfo> {
    R<SysUser> getUser(String token);
}
