package com.esfm.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.system.dao.SysSourceDao;
import com.esfm.modules.system.entity.SysSource;
import com.esfm.modules.system.service.SysSourceService;
import org.springframework.stereotype.Service;

/**
 * 系统菜单表(SysSource)表服务实现类
 *
 * @author makejava
 * @since 2021-09-14 13:08:29
 */
@Service("sysSourceService")
public class SysSourceServiceImpl extends ServiceImpl<SysSourceDao, SysSource> implements SysSourceService {

    @Override
    public String getWxAccessToken() {
        return baseMapper.selectOne(new QueryWrapper<SysSource>().eq("temp_key", "wx_access_token")).getTempValue();
    }
}
