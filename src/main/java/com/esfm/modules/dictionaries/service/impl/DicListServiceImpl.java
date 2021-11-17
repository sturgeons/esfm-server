package com.esfm.modules.dictionaries.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.CommConstant;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.dictionaries.dao.DicListDao;
import com.esfm.modules.dictionaries.entity.DicItem;
import com.esfm.modules.dictionaries.entity.DicList;
import com.esfm.modules.dictionaries.service.DicItemService;
import com.esfm.modules.dictionaries.service.DicListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典清单(DicList)表服务实现类
 * @author yaoxin
 * @since 2020-08-07 16:58:54
 */
@Service("dicListService")
public class DicListServiceImpl extends ServiceImpl<DicListDao, DicList> implements DicListService {

    @Resource
    private DicItemService dicItemService;

    private R<?> getItem(String name) {
        DicList dicListObj;
        dicListObj = getBaseMapper().selectOne(new QueryWrapper<DicList>().eq("name", name));
        if (dicListObj == null) {
            return R.failed(ResponseInfoConstant.OPERATE_FAIL);
        }
        return R.ok(dicListObj);
    }

    @Override
    public R<?> getMapByName(String name) {
        var dicObj = getItem(name);
        if (dicObj.ok()) {
            var res = new ArrayList<>();
            var dic = (DicList) dicObj.getData();
            var dicItems = dicItemService.list(new QueryWrapper<DicItem>().eq("dic_list_id", dic.getId()).orderByAsc("option_sort"));
            dicItems.forEach(t -> {
                JSONObject obj = new JSONObject();
                obj.put("key", t.getOptionText());
                obj.put("value", t.getOptionValue());
                res.add(obj);
            });
            return R.ok(res);
        }
        return dicObj;
    }

    @Override
    public R<?> getArray(String name) {
        var dicObj = getItem(name);
        if (dicObj.ok()) {
            var dic = (DicList) dicObj.getData();
            if (dic.getType().equals(CommConstant.DIC_TABLE_FLAG)){
               return R.ok( getBaseMapper().getArrayFromTable(dic.getTableName(),dic.getFieldName(),dic.getFieldValue()));
            }
            var dicItems = dicItemService.list(new QueryWrapper<DicItem>().eq("dic_list_id", dic.getId()).orderByAsc("option_sort"));
            List<String> res = new ArrayList<>();
            dicItems.forEach(t -> res.add(t.getOptionValue()));
            return R.ok(res);
        }
        return dicObj;
    }
}
