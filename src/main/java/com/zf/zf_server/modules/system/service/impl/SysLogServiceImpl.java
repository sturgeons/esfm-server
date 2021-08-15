package com.zf.zf_server.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.modules.system.dao.SysLogDao;
import com.zf.zf_server.modules.system.entity.SysLog;
import com.zf.zf_server.modules.system.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * 系统日志表(SysLog)表服务实现类
 *
 * @author yaoxin
 * @since 2020-05-27 15:14:53
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

}