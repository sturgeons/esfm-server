package com.esfm.modules.wizard.service;

import com.esfm.extension.api.R;
import com.esfm.modules.wizard.entity.bo.ObsBo;
import org.springframework.web.multipart.MultipartFile;

public interface ObsService {
    R<ObsBo> upload(MultipartFile file);
}
