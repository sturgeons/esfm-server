package com.esfm.extension.runner;

import com.esfm.modules.system.entity.SysSource;
import com.esfm.modules.system.service.SysSourceService;
import com.esfm.utils.YaoSay;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DefaultApplicationRunner implements ApplicationRunner {
    @Resource
    SysSourceService sysSourceService;

    @Override
    public void run(ApplicationArguments args) {
//        List<SysSource> list = sysSourceService.list();
//        YaoSay.e(list);
    }
}
