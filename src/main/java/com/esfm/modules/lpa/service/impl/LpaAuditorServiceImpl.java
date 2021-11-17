package com.esfm.modules.lpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.system.entity.SysUser;
import com.esfm.modules.system.service.SysUserService;
import com.esfm.modules.lpa.dao.LpaAuditorDao;
import com.esfm.modules.lpa.entity.LpaAuditor;
import com.esfm.modules.lpa.service.LpaAuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分层审核-审核员(LpaAuditor)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:28:16
 */
@Service("lpaAuditorService")
public class LpaAuditorServiceImpl extends ServiceImpl<LpaAuditorDao, LpaAuditor> implements LpaAuditorService {

    @Resource
    SysUserService sysUserService;
    @Override
    public R<?> add(LpaAuditor lpaAuditor) {
        var count=this.baseMapper.selectCount(new QueryWrapper<LpaAuditor>().eq("user_id",lpaAuditor.getUserId()));
        if (count>0){
            return R.failed(ResponseInfoConstant.HAVE_SAME_ITEM);
        }

        var sysUser=sysUserService.getOne(new QueryWrapper<SysUser>().eq("id",lpaAuditor.getUserId()));
        lpaAuditor.setUserName(sysUser.getName());
        lpaAuditor.setPid("0");
        var res=this.save(lpaAuditor);
        if (res){
            return R.ok(ResponseInfoConstant.OPERATE_SUCCESS);
        }
        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public R<?> performance() {
        return R.ok(this.baseMapper.performance());
    }
}
