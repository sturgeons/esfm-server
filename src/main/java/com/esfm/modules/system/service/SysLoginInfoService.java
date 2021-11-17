package com.esfm.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.system.entity.SysLoginInfo;
import com.esfm.modules.system.entity.SysUser;

/**
 * 登录历史(SysLoginInfo)表服务接口
 *
 * @author yaoxin
 * @since 2020-07-12 13:54:03
 */
public interface SysLoginInfoService extends IService<SysLoginInfo> {
    SysUser getUser(String token);

    R<SysLoginInfo> getLoginInfo(String accessToken);
}
