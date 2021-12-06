package com.esfm.modules.productionCenter.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.modules.productionCenter.dao.PdEscalationQrDtlDao;
import com.esfm.modules.productionCenter.entity.PdEscalationQrDtl;
import com.esfm.modules.productionCenter.entity.PdEscalationQrPicture;
import com.esfm.modules.productionCenter.service.PdEscalationQrDtlService;
import com.esfm.modules.productionCenter.service.PdEscalationQrPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 升级汇报流程-快速响应(PdEscalationQrDtl)表服务实现类
 *
 * @author yaoxin
 * @since 2021-12-03 22:32:18
 */
@Service("pdEscalationQrDtlService")
public class PdEscalationQrDtlServiceImpl extends ServiceImpl<PdEscalationQrDtlDao, PdEscalationQrDtl> implements PdEscalationQrDtlService {
    @Resource
    PdEscalationQrPictureService pdEscalationQrPictureService;
    static final String FILE_TYPE_ISSUE = "ISSUE";
    static final String FILE_TYPE_CONTAIN_PICTURE = "CONTAIN";
    static final String STATE_FLAG_ONGOING = "ON_GOING";

    @Override
    public R<?> submit(PdEscalationQrDtl pdEscalationQrDtl) {
        pdEscalationQrDtl.setStateFlag(STATE_FLAG_ONGOING);
        save(pdEscalationQrDtl);
        savePicture(pdEscalationQrDtl);
        return R.ok(pdEscalationQrDtl);
    }

    private void savePicture(PdEscalationQrDtl pdEscalationQrDtl) {
        for (String t : pdEscalationQrDtl.getPictures()) {
            PdEscalationQrPicture pdEscalationQrPicture = new PdEscalationQrPicture();
            pdEscalationQrPicture.setFileId(t);
            pdEscalationQrPicture.setQrId(pdEscalationQrDtl.getId());
            pdEscalationQrPicture.setFileType(FILE_TYPE_ISSUE);
            pdEscalationQrPictureService.save(pdEscalationQrPicture);
        }
        for (String t : pdEscalationQrDtl.getContainPictures()) {
            PdEscalationQrPicture pdEscalationQrPicture = new PdEscalationQrPicture();
            pdEscalationQrPicture.setFileId(t);
            pdEscalationQrPicture.setQrId(pdEscalationQrDtl.getId());
            pdEscalationQrPicture.setFileType(FILE_TYPE_CONTAIN_PICTURE);
            pdEscalationQrPictureService.save(pdEscalationQrPicture);
        }
    }

    @Override
    public R<?> updateQr(PdEscalationQrDtl pdEscalationQrDtl) {
        //首先删除原先的照片内容 然后插入新的照片
        LambdaQueryChainWrapper<PdEscalationQrPicture> oldPdEscalationQrPictures = pdEscalationQrPictureService.lambdaQuery().eq(PdEscalationQrPicture::getQrId, pdEscalationQrDtl.getId());
        pdEscalationQrPictureService.remove(oldPdEscalationQrPictures);
        updateById(pdEscalationQrDtl);
        savePicture(pdEscalationQrDtl);
        return R.ok(pdEscalationQrDtl);
    }
}