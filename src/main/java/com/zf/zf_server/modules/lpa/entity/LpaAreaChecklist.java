package com.zf.zf_server.modules.lpa.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 分层审核-区域和审核单匹配表(LpaAreaChecklist)表实体类
 * @author yaoxin
 * @since 2020-07-30 11:27:59
 */
@Data
public class LpaAreaChecklist extends Model<LpaAreaChecklist> {
    //审核区域 索引
    @Excel(name = "审核区域 索引", width = 20)
    private String areaId;
    //审核单 索引
    @Excel(name = "审核单 索引", width = 20)
    private String checklistId;
    //id
    @Excel(name = "id", width = 20)
    private String id;

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override

    public Serializable pkVal() {
        return this.id;
    }
}
