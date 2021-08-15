package com.zf.zf_server.modules.system.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    String id;
    String path;
    String icon;
    String title;
    Integer sortNum;
    List<MenuChildrenVo> children;
}

