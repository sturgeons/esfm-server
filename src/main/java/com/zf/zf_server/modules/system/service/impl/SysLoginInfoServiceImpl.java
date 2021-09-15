package com.zf.zf_server.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.system.dao.SysLoginInfoDao;
import com.zf.zf_server.modules.system.entity.SysLoginInfo;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysLoginInfoService;
import com.zf.zf_server.modules.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录历史(SysLoginInfo)表服务实现类
 *
 * @author yaoxin
 * @since 2020-07-12 13:54:03
 */
@Service("sysLoginInfoService")
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoDao, SysLoginInfo> implements SysLoginInfoService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public R<SysUser> getUser(String token) {
        var info = baseMapper.selectById(token);
        if (info == null) {
            return R.failed(ResponseInfoConstant.NO_LOGIN_INFO);
        }
        var user = sysUserService.getById(info.getUserId());
        return R.ok(user);
    }
}
