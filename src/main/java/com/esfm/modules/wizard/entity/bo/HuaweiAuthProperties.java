package com.esfm.modules.wizard.entity.bo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "huawei-auth")
public class HuaweiAuthProperties {
    private String ak;
    private String sk;
    private String endpoint;
}
