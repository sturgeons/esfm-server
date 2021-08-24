package com.zf.zf_server.modules.lpa.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.lpa.dao.LpaScheduleDao;
import com.zf.zf_server.modules.lpa.entity.LpaAuditor;
import com.zf.zf_server.modules.lpa.entity.LpaQuestion;
import com.zf.zf_server.modules.lpa.entity.LpaSchedule;
import com.zf.zf_server.modules.lpa.service.*;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysLoginInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分层审核-审核计划(LpaSchedule)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:30:11
 */
@Service("lpaScheduleService")
public class LpaScheduleServiceImpl extends ServiceImpl<LpaScheduleDao, LpaSchedule> implements LpaScheduleService {

    @Resource
    private LpaQuestionService lpaQuestionService;

    @Resource
    private LpaAreaService lpaAreaService;
    @Resource
    private LpaAuditorService lpaAuditorService;
    @Resource
    private LpaChecklistService lpaChecklistService;
    //员工登录信息服务
    @Resource
    private SysLoginInfoService sysLoginInfoService;

    @Override
    public R<?> reAudit(String id) {
        var schedule = this.baseMapper.selectById(id);
        schedule.setStatus(0);
        schedule.setFinishDate(null);
        schedule.updateById();
        lpaQuestionService.remove(new QueryWrapper<LpaQuestion>().eq("schedule_id", id));
        return R.ok(schedule);
    }

    @Override
    public R<?> month(String month) {
        var lpaScheduleMonthList = this.baseMapper.month(month);
        List<JSONObject> lpaScheduleMonthJson = new ArrayList<>();
        lpaScheduleMonthList.forEach(t -> {
            JSONObject object = new JSONObject();
            object.put("id", t.getId());
            object.put("start", DateUtil.formatDate(t.getPlanDate()));
            object.put("title", t.getAuditorName() + "-" + t.getAreaName() + "-" + t.getCheckName());
            if (t.getStatus() == 0) {
                object.put("backgroundColor", "red");
            }
            lpaScheduleMonthJson.add(object);
        });
        return R.ok(lpaScheduleMonthJson);
    }

    @Override
    public R<?> newSchedule(LpaSchedule lpaSchedule) {
        var areaName = lpaAreaService.getById(lpaSchedule.getAreaId()).getArea();
        var checkName = lpaChecklistService.getById(lpaSchedule.getChecklistId()).getName();
        var auditorName = lpaAuditorService.getById(lpaSchedule.getAuditorId()).getUserName();
        lpaSchedule.setAreaName(areaName);
        lpaSchedule.setCheckName(checkName);
        lpaSchedule.setAuditorName(auditorName);
        if (this.baseMapper.insert(lpaSchedule) == 1) {
            return R.ok(lpaSchedule);
        }

        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public R<?> getScheduleByUser(Page<LpaSchedule> page, String token, String status) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(token);
        if (!sysUserR.ok()) {
            return sysUserR;
        }
        LpaAuditor lpaAuditor = lpaAuditorService.getOne(new QueryWrapper<LpaAuditor>().eq("user_id", sysUserR.getData().getId()));
        Map<String, String> maps = new HashMap<>();
        if (lpaAuditor==null){
            return  R.failed(ResponseInfoConstant.NOT_LPA_AUDITOR);
        }
        maps.put("auditor_id", lpaAuditor.getId());
        maps.put("status", status);
        DateTime offsetDate = DateTime.now().offset(DateField.HOUR, 24);
        var schedules = this.baseMapper.selectPage(page, new QueryWrapper<LpaSchedule>().allEq(maps).lt("plan_date", offsetDate.toString()).orderByDesc("plan_date"));
        return R.ok(schedules);
    }

    @Override
    public R<?> close(LpaSchedule lpaSchedule) {
        return finishLpaSchedule(lpaSchedule);
    }

    @Override
    public R<?> noProduction(LpaSchedule lpaSchedule) {
        lpaSchedule.setCommit("无生产");
        return finishLpaSchedule(lpaSchedule);
    }

    private R<?> finishLpaSchedule(LpaSchedule lpaSchedule) {
        lpaSchedule.setStatus(1);
        lpaSchedule.setFinishDate(DateTime.now());
        var sourceLpaSchedule=this.baseMapper.selectById(lpaSchedule.getId());
        lpaSchedule.setSpendDays((int) DateUtil.between(sourceLpaSchedule.getPlanDate(),DateTime.now(), DateUnit.DAY));
        int res = this.baseMapper.updateById(lpaSchedule);
        return res > 0 ? R.ok(lpaSchedule) : R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public R<?> getCloseListByUser(Page<LpaSchedule> page, String token) {
        R<SysUser> sysUserR = sysLoginInfoService.getUser(token);
        if (!sysUserR.ok()) {
            return sysUserR;
        }
        LpaAuditor lpaAuditor = lpaAuditorService.getOne(new QueryWrapper<LpaAuditor>().eq("user_id", sysUserR.getData().getId()));
        Map<String, String> maps = new HashMap<>();
        maps.put("auditor_id", lpaAuditor.getId());
        maps.put("status", "1");
        var schedules = this.baseMapper.selectPage(page, new QueryWrapper<LpaSchedule>().allEq(maps).orderByDesc("plan_date"));
        return R.ok(schedules);
    }
}
