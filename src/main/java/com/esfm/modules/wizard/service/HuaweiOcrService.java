package com.esfm.modules.wizard.service;

import com.esfm.extension.api.R;

public interface HuaweiOcrService {
    R<?> getOcr(String url);

    R<?> getOcrBase64(String imgStr);
}
