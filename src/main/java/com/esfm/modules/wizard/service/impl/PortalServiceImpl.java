package com.esfm.modules.wizard.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.CommConstant;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.system.entity.SysLoginInfo;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysLoginInfoService;
import com.esfm.modules.system.service.SysUserService;
import com.esfm.modules.wizard.service.PortalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 前段服务实现
 *
 * @author yaoxin
 * @since 2020-04-22 14:15:32
 */

@Service("portalService")
public class PortalServiceImpl implements PortalService {

    // 用户服务
    @Resource
    private SysUserService sysUserService;
    // 用户登录服务
    @Resource
    private SysLoginInfoService sysLoginInfoService;

    /*
    * 20/07/11
    * 新增登录方式
    * 使用sysUser表
    * **/
    @Override
    public R<?> loginDoBySysUserByTel(String tel, String password) {
        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("tel",tel);
        queryMap.put("password",password);
        var sysUser=sysUserService.list(new QueryWrapper<SysUser>().allEq(queryMap));
        //判断是否存在用户
        if(sysUser.size()==0){
            return R.failed(ResponseInfoConstant.ERROR_LOGIN_INFO);
        }
        //判断是否已经通过验证
        if (sysUser.get(0).getApproveFlag().equals(CommConstant.NOT_APPROVE)){
            return R.failed(ResponseInfoConstant.ACCOUNT_NOT_APPROVE);
        }
        //判断账号是否已经冻结
        if(sysUser.get(0).getStatus()==CommConstant.FREEZE){
            return R.failed(ResponseInfoConstant.ERROR_LOGIN_FREEZE);
        }
        //准备登录信息
        var sysLoginInfo=new SysLoginInfo();
        sysLoginInfo.setLoginDate(DateUtil.date());
        sysLoginInfo.setExpiryTime( DateUtil.offsetDay(DateUtil.date(), CommConstant.EXPIRY_INTERVAL));
        sysLoginInfo.setUserId(sysUser.get(0).getId());
        sysLoginInfo.setStatus(CommConstant.NORMAL);
        //存储数据
        sysLoginInfoService.save(sysLoginInfo);
        // 读取手机端登录用户的权限

        // 返回token和权限
        JSONObject res=new JSONObject();
        res.set("token", sysLoginInfo.getId());
        res.set("name", sysUser.get(0).getName());
        res.set("uuid", sysUser.get(0).getId());
       return R.ok(res);
    }
}
