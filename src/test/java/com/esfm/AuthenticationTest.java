package com.esfm;

import cn.hutool.db.ds.druid.DruidDSFactory;
import com.esfm.extension.api.R;
import com.esfm.modules.system.entity.SysLoginInfo;
import com.esfm.modules.system.service.SysLoginInfoService;
import com.esfm.modules.system.service.SysUserService;
import com.zaxxer.hikari.HikariConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Properties;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysLoginInfoService sysLoginInfoService;
    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("mark","123456","admin");
    }
    @Test
    public void testAuthentication() {
        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("mark","123456");
        subject.login(token);

        System.out.println(subject.isAuthenticated());
        sysUserService.list().forEach(System.out::println);
    }
    @Test
    public void dateTest() {
        R<SysLoginInfo> loginInfo = sysLoginInfoService.getLoginInfo("1458095041506521089");
        System.out.println(loginInfo.getData().getExpiryTime());
        System.out.println(new Date());
        System.out.println(new Date().before(loginInfo.getData().getExpiryTime()));


    }
}
