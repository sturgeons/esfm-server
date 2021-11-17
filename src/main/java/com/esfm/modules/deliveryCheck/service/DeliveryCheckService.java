package com.esfm.modules.deliveryCheck.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.deliveryCheck.entity.DeliveryCheck;
import org.springframework.web.multipart.MultipartFile;

/**
 * Vqe主积分表
 * (DeliveryCheck)表服务接口
 *
 * @author makejava
 * @since 2021-09-01 14:55:44
 */
public interface DeliveryCheckService extends IService<DeliveryCheck> {

    R<?> uploadPicture(MultipartFile files);

    R<?> analysisImage(String imgStr);
}