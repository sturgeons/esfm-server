package com.zf.zf_server.modules.salary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.salary.dao.SalaryTemplateItemDao;
import com.zf.zf_server.modules.salary.entity.SalaryTemplateItem;
import com.zf.zf_server.modules.salary.service.SalaryTemplateItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员历史薪资模板子类(SalaryTemplateItem)表服务实现类
 *
 * @author makejava
 * @since 2021-06-01 19:41:21
 */
@Service("salaryTemplateItemService")
public class SalaryTemplateItemServiceImpl extends ServiceImpl<SalaryTemplateItemDao, SalaryTemplateItem> implements SalaryTemplateItemService {

    @Override
    public R<?> getItemByTemplateId(String templateId) {
        List<String> list=new ArrayList<>();
        List<SalaryTemplateItem> items= baseMapper.selectList(new QueryWrapper<SalaryTemplateItem>().eq("template_id",templateId));
        items.forEach(t-> list.add(t.getColumnsName()));
        return R.ok(list);
    }

    @Override
    public R<?> saveSalaryItems(List<SalaryTemplateItem> items) {
        if(items.size()<1){
            return R.failed(ResponseInfoConstant.OPERATE_FAIL);
        }
        //删除数据库中的数据
        baseMapper.delete(new QueryWrapper<SalaryTemplateItem>().eq("template_id",items.get(0).getTemplateId()));
        items.forEach(t-> baseMapper.insert(t));

        return R.ok(items);
    }
}