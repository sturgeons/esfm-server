package com.esfm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.utils.YaoSay;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            if (sysUser != null) {
                this.setFieldValByName("createBy", sysUser.getName(), metaObject);
                this.setFieldValByName("updateBy", sysUser.getName(), metaObject);
            }
        } catch (Exception ignored) {
        }
        //根据属性名称设置值
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            if (sysUser != null) {
                this.setFieldValByName("updateBy", sysUser.getName(), metaObject);
            }
        } catch (Exception ignored) {
        }
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
