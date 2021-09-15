package com.zf.zf_server.modules.wizard.service;

import com.zf.zf_server.extension.api.R;

public interface HuaweiOcrService {
    R<?> getOcr(String url);

    R<?> getOcrBase64(String imgStr);
}
