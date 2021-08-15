package com.zf.zf_server.modules.lpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.lpa.dao.LpaAuditorDao;
import com.zf.zf_server.modules.lpa.entity.LpaAuditor;
import com.zf.zf_server.modules.lpa.service.LpaAuditorService;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分层审核-审核员(LpaAuditor)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:28:16
 */
@Service("lpaAuditorService")
public class LpaAuditorServiceImpl extends ServiceImpl<LpaAuditorDao, LpaAuditor> implements LpaAuditorService {

    @Autowired
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
}
