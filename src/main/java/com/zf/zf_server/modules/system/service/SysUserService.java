package com.zf.zf_server.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUser)表服务接口
 *
 * @author yaoxin
 * @since 2020-05-27 15:42:13
 */
public interface SysUserService extends IService<SysUser> {

    R<?> getRoles(String id);

    R<?> updateRoles(List<Map<String, String>> userRolesObj);

    R<?> getMenus(String token, String terminal);

    R<?> register(SysUser sysUser);

    R<?> changePassword(String oldPassword, String newPassword, String header);

    //存储微信用户的token
    R<?> saveWxOpenId(String token, String openid);

    R<SysUser> getUserInfo(String token);

    R<?> checkWxAuth(String token);
}
