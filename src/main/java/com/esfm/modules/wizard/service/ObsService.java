package com.esfm.modules.wizard.service;

import com.esfm.extension.api.R;
import org.springframework.web.multipart.MultipartFile;

public interface ObsService {
    R<?> upload(MultipartFile file);
}
