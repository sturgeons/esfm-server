package com.esfm.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import com.esfm.modules.system.entity.SysFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储(SysFile)表服务接口
 *
 * @author yaoxin
 * @since 2021-11-30 14:38:20
 */
public interface SysFileService extends IService<SysFile> {

    R<?> updatePicture(MultipartFile file);
}