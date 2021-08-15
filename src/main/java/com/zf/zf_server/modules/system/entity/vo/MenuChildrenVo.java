package com.zf.zf_server.modules.system.entity.vo;

import lombok.Data;

@Data

public class MenuChildrenVo {
    String id;
    String path;
    String icon;
    String title;
    Integer sortNum;
}
