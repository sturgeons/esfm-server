package com.esfm.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.ApiException;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.extension.api.R;
import com.esfm.modules.system.dao.SysLoginInfoDao;
import com.esfm.modules.system.entity.SysLoginInfo;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysLoginInfoService;
import com.esfm.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    @Resource
    private SysUserService sysUserService;

    @Override
    public SysUser getUser(String token) {
        var info = baseMapper.selectById(token);
        if (info == null) {
            throw new ApiException(ResponseInfoConstant.NO_LOGIN_INFO);
        }
        return sysUserService.getById(info.getUserId());
    }

    @Override
    public R<SysLoginInfo> getLoginInfo(String accessToken) {
        SysLoginInfo sysLoginInfo = this.getById(accessToken);
        if (sysLoginInfo == null) {
            return R.failed(ResponseInfoConstant.NO_LOGIN_INFO);
        }
        return R.ok(sysLoginInfo);
    }
}
