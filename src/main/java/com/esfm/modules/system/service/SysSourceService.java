package com.esfm.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.modules.system.entity.SysSource;

/**
 * 系统菜单表(SysSource)表服务接口
 *
 * @author makejava
 * @since 2021-09-14 13:08:29
 */
public interface SysSourceService extends IService<SysSource> {

    String getWxAccessToken();
}
