package com.esfm.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.modules.system.dao.SysFileDao;
import com.esfm.modules.system.entity.SysFile;
import com.esfm.modules.system.service.SysFileService;
import com.esfm.modules.wizard.entity.bo.ObsBo;
import com.esfm.modules.wizard.service.ObsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件存储(SysFile)表服务实现类
 *
 * @author yaoxin
 * @since 2021-11-30 14:38:20
 */
@Service("sysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileDao, SysFile> implements SysFileService {

    @Resource
    ObsService obsService;

    @Override
    public R<?> updatePicture(MultipartFile file) {
        R<ObsBo> obsRes = obsService.upload(file);
        SysFile sysFile=new SysFile();
        sysFile.setFilename(obsRes.getData().getFilename());
        sysFile.setUrl(obsRes.getData().getUrl());
        sysFile.setMd5(obsRes.getData().getMd5Filename());
        this.save(sysFile);
        return R.ok(sysFile);
    }
}