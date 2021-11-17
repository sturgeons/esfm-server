package com.esfm.modules.deliveryCheck.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.deliveryCheck.dao.DeliveryCheckPictureDao;
import com.esfm.modules.deliveryCheck.entity.DeliveryCheckPicture;
import com.esfm.modules.deliveryCheck.service.DeliveryCheckPictureService;
import org.springframework.stereotype.Service;

/**
 * 发货检验的照片(DeliveryCheckPicture)表服务实现类
 *
 * @author makejava
 * @since 2021-09-30 08:51:53
 */
@Service("deliveryCheckPictureService")
public class DeliveryCheckPictureServiceImpl extends ServiceImpl<DeliveryCheckPictureDao, DeliveryCheckPicture> implements DeliveryCheckPictureService {

}
