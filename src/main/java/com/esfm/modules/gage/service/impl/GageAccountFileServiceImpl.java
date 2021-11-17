package com.esfm.modules.gage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.gage.dao.GageAccountFileDao;
import com.esfm.modules.gage.entity.GageAccountFile;
import com.esfm.modules.gage.service.GageAccountFileService;
import org.springframework.stereotype.Service;

/**
 * 检具台账附件(GageAccountFile)表服务实现类
 *
 * @author makejava
 * @since 2021-10-24 21:18:28
 */
@Service("gageAccountFileService")
public class GageAccountFileServiceImpl extends ServiceImpl<GageAccountFileDao, GageAccountFile> implements GageAccountFileService {

}
