package com.esfm.modules.productionCenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.productionCenter.entity.PdDtl;
import com.esfm.modules.productionCenter.entity.PdEscalationQrDtl;

/**
 * 升级汇报流程-快速响应(PdEscalationQrDtl)表服务接口
 *
 * @author yaoxin
 * @since 2021-12-03 22:32:18
 */
public interface PdEscalationQrDtlService extends IService<PdEscalationQrDtl> {

    R<?> submit(PdEscalationQrDtl pdEscalationQrDtl);

    R<?> updateQr(PdEscalationQrDtl pdEscalationQrDtl);
}