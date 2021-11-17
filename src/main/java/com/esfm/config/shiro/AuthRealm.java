package com.esfm.config.shiro;

import com.esfm.extension.api.R;
import com.esfm.modules.system.entity.SysLoginInfo;
import com.esfm.modules.system.entity.SysRole;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysLoginInfoService;
import com.esfm.modules.system.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.checkerframework.checker.units.qual.A;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class AuthRealm extends AuthorizingRealm {
    @Resource
    @Lazy
    SysLoginInfoService sysLoginInfoService;

    @Resource
    @Lazy
    SysUserService sysUserService;
    //添加权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        //Integer userId = user.getUserId();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<SysRole> rolesEntityList = sysUserService.getRolesEntityList(user.getId());
        rolesEntityList.forEach(role -> simpleAuthorizationInfo.addRole(role.getCode()));
        return simpleAuthorizationInfo;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        R<SysLoginInfo> loginInfo = sysLoginInfoService.getLoginInfo(accessToken);
        //2. token失效
        if (!loginInfo.ok() || loginInfo.getData().getExpiryTime().before(new Date())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        SysUser user = sysLoginInfoService.getUser(accessToken);
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user==null) {
            throw new UnknownAccountException("用户不存在!");
        }
//        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        return new SimpleAuthenticationInfo(user, accessToken, this.getName());
//        return null;
    }
}
