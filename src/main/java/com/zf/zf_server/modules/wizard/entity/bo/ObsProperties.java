package com.zf.zf_server.modules.wizard.entity.bo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "obs")
public class ObsProperties {
    private String as;
    private String sk;
    private String endpoint;
}
