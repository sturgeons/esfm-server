package com.esfm.modules.system.entity.vo;

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

