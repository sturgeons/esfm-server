package com.zf.zf_server.modules.lpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.lpa.entity.LpaAuditor;

/**
 * 分层审核-审核员(LpaAuditor)表服务接口
 * @author yaoxin
 * @since 2020-07-30 11:28:16
 */
public interface LpaAuditorService extends IService<LpaAuditor> {

    R<?> add(LpaAuditor lpaAuditor);
}
