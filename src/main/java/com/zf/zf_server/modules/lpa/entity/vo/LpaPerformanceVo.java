package com.zf.zf_server.modules.lpa.entity.vo;

import lombok.Data;

@Data
public class LpaPerformanceVo {
    private String auditorName;
    private Integer complete;
    private Integer unComplete;
    private Float avaSpendDays;
}
