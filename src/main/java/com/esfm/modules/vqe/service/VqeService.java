package com.esfm.modules.vqe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.vqe.entity.Vqe;

/**
 * Vqe主积分表
 * (Vqe)表服务接口
 *
 * @author makejava
 * @since 2021-09-08 16:12:20
 */
public interface VqeService extends IService<Vqe> {

    R<?> sign(String token);

    R<?> getSign(String token, String year, String month);

    R<?> signTrain(String token);

    R<?> getSignTrain(String token);

    R<?> getAllSign(String token);

    R<?> getSignTrainWeek(String header);
}
