package com.esfm.modules.vqe.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysLoginInfoService;
import com.esfm.modules.vqe.dao.VqeDao;
import com.esfm.modules.vqe.entity.Vqe;
import com.esfm.modules.vqe.service.VqeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vqe主积分表
 * (Vqe)表服务实现类
 *
 * @author makejava
 * @since 2021-09-08 16:12:20
 */
@Service("vqeService")
public class VqeServiceImpl extends ServiceImpl<VqeDao, Vqe> implements VqeService {
    private static final String CONTEXT_SIGN = "sign";
    private static final String CONTEXT_TRAIN = "train";
    @Resource
    SysLoginInfoService sysLoginInfoService;

    @Override
    public R<?> sign(String token) {
        SysUser user = sysLoginInfoService.getUser(token);

        Vqe vqe = new Vqe();
        vqe.setUserId(user.getId());
        vqe.setSignDate(DateTime.now());
        vqe.setContext("sign");
        List<Vqe> vqeList = this.lambdaQuery().eq(Vqe::getUserId, user.getId())
                .eq(Vqe::getContext, CONTEXT_SIGN)
                .eq(Vqe::getSignDate, DateTime.now().toDateStr())
                .list();
        if (vqeList.size() > 0) {
            return R.failed(ResponseInfoConstant.HAVE_SAME_ITEM);
        }
        this.save(vqe);
        return R.ok(vqe);
    }

    @Override
    public R<?> getSign(String token, String year, String month) {
        Date selectDate = DateUtil.parse(year + "-" + month + "-01");
        SysUser user = sysLoginInfoService.getUser(token);
        JSONObject res = new JSONObject();
        List<Vqe> list = this.lambdaQuery().eq(Vqe::getUserId, user.getId())
                .eq(Vqe::getContext, CONTEXT_SIGN)
                .eq(Vqe::getSignDate, DateTime.now().toDateStr())
                .list();
        if (list.size() > 0) {
            res.put("isSign", true);
        } else {
            res.put("isSign", false);
        }
        list = this.list(new QueryWrapper<Vqe>().eq("user_id", user.getId()).between("sign_date", DateUtil.beginOfMonth(selectDate), DateUtil.endOfMonth(selectDate)));
        if (list.size() > 0) {
            res.put("list", list);
        } else {
            res.put("list", new JSONArray());
        }
        long count = this.lambdaQuery().eq(Vqe::getUserId, user.getId()).eq(Vqe::getContext, CONTEXT_SIGN).count();
        res.put("count", count);
        return R.ok(res);
    }

    @Override
    public R<?> signTrain(String token) {
        SysUser user = sysLoginInfoService.getUser(token);
        Vqe vqe = new Vqe();
        vqe.setUserId(user.getId());
        vqe.setContext("train");
        vqe.setSignDate(new Date());
        this.save(vqe);
        return R.ok(vqe);
    }

    @Override
    public R<?> getSignTrain(String token) {
        SysUser user = sysLoginInfoService.getUser(token);
        return R.ok(this.lambdaQuery().eq(Vqe::getContext, CONTEXT_TRAIN).eq(Vqe::getUserId, user.getId()).count());
    }

    @Override
    public R<?> getAllSign(String token) {
        SysUser user = sysLoginInfoService.getUser(token);
        return R.ok(this.lambdaQuery().eq(Vqe::getContext, CONTEXT_SIGN).eq(Vqe::getUserId, user.getId()).count());
    }

    @Override
    public R<?> getSignTrainWeek(String token) {
        boolean res = false;
        SysUser user = sysLoginInfoService.getUser(token);
        List<Vqe> VQEs = this.lambdaQuery().eq(Vqe::getUserId, user.getId()).eq(Vqe::getContext, "train").orderByDesc(Vqe::getSignDate).list();
        if (VQEs.size() > 0) {
            res = DateUtil.weekOfYear(new Date()) == DateUtil.weekOfYear(VQEs.get(0).getSignDate());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("canSign", !res);
        return R.ok(jsonObject);
    }
}
