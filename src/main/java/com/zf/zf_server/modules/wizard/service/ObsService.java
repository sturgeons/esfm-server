package com.zf.zf_server.modules.wizard.service;

import com.zf.zf_server.extension.api.R;
import org.springframework.web.multipart.MultipartFile;

public interface ObsService {
    R<?> upload(MultipartFile file);
}
