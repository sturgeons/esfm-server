package com.zf.zf_server.modules.lpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.lpa.entity.LpaPicture;
import org.springframework.web.multipart.MultipartFile;

/**
 * 分层审核-图片(LpaPicture)表服务接口
 * @author yaoxin
 * @since 2020-07-30 11:29:03
 */
public interface LpaPictureService extends IService<LpaPicture> {

    R<?> uploadPicture(MultipartFile file, LpaPicture lpaPicture);
}
