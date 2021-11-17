package com.esfm.modules.salary.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysLoginInfoService;
import com.esfm.modules.salary.dao.SalaryHistoryDao;
import com.esfm.modules.salary.entity.SalaryHistory;
import com.esfm.modules.salary.service.SalaryHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员历史薪资(SalaryHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-06-01 19:40:39
 */
@Service("salaryHistoryService")
public class SalaryHistoryServiceImpl extends ServiceImpl<SalaryHistoryDao, SalaryHistory> implements SalaryHistoryService {
    //员工登录信息服务
    @Resource
    private SysLoginInfoService sysLoginInfoService;

    @Override
    public R<?> upload(List<SalaryHistory> SalaryHistorys) {
        //每个信息要自己匹配是否有重复情况
        //每一个薪资都要与数据库信息匹配是否有重复
        int sameCount = 0, saveCount = 0;
        if (SalaryHistorys != null && SalaryHistorys.size() > 0) {
            //重复的薪资信息 code为唯一判断条件
            List<SalaryHistory> sameSalaryHistorys = new ArrayList<>();
            for (SalaryHistory salaryHistory : SalaryHistorys) {
                //判断数据库是否有重复的code和年月
                Map<String, String> query = new HashMap<>();
                query.put("year", salaryHistory.getYear());
                query.put("month", salaryHistory.getMonth());
                query.put("work_code", salaryHistory.getWorkCode());
                query.put("show_enable", "1");
                SalaryHistory DbSalaryHistory = baseMapper.selectOne(new QueryWrapper<SalaryHistory>().allEq(query));
                salaryHistory.setShowEnable("1");
                if (DbSalaryHistory != null) {
                    //新数据存为1
                    DbSalaryHistory.setShowEnable("0");
                    DbSalaryHistory.updateById();
                    //插入相同code 的信息信息
                    sameSalaryHistorys.add(salaryHistory);
                    //插入数据库重复条目信息
                    sameSalaryHistorys.add(DbSalaryHistory);
                    sameCount++;
                }
                //存储当前薪资条目
                saveCount = saveCount + baseMapper.insert(salaryHistory);
            }
            JSONObject res = new JSONObject();
            res.set("sameCount", sameCount);
            res.set("sameList", sameSalaryHistorys);
            res.set("saveCount", saveCount);
            return R.ok(res);
        }
        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    //  获取当前用户的历史薪资
    @Override
    public R<?> getHistorys(String token) {
        //获取用户信息
        SysUser sysUserR = sysLoginInfoService.getUser(token);
        String workCode = sysUserR.getWorkNo();
        String name = sysUserR.getName();

        if (StrUtil.isNotBlank(workCode) && StrUtil.isNotBlank(name)) {
            JSONObject res=new JSONObject();
            res.set("name", name);
            res.set("workCode", workCode);
            res.set("data", baseMapper.selectAllHistory(name, workCode));
            return R.ok(res);
        } else {
            return R.failed(ResponseInfoConstant.STRING_IS_BLANK);
        }

    }
}